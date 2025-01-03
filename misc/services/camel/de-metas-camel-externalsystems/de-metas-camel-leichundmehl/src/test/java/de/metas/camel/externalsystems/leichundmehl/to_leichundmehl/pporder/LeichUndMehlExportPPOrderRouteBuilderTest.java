/*
 * #%L
 * de-metas-camel-leichundmehl
 * %%
 * Copyright (C) 2022 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package de.metas.camel.externalsystems.leichundmehl.to_leichundmehl.pporder;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.metas.camel.externalsystems.common.JsonObjectMapperHolder;
import de.metas.camel.externalsystems.common.LogMessageRequest;
import de.metas.camel.externalsystems.common.ProcessLogger;
import de.metas.camel.externalsystems.common.v2.RetrieveProductCamelRequest;
import de.metas.camel.externalsystems.leichundmehl.to_leichundmehl.DispatchMessageRequest;
import de.metas.common.externalsystem.JsonExternalSystemRequest;
import de.metas.common.rest_api.v2.adprocess.JsonAdProcessRequest;
import de.metas.common.rest_api.v2.attachment.JsonAttachment;
import de.metas.common.rest_api.v2.attachment.JsonAttachmentRequest;
import lombok.NonNull;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Properties;

import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_AD_Process_ROUTE_ID;
import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_ATTACHMENT_ROUTE_ID;
import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_LOG_MESSAGE_ROUTE_ID;
import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_RETRIEVE_MATERIAL_PRODUCT_INFO_V2_CAMEL_ROUTE_ID;
import static de.metas.camel.externalsystems.common.ExternalSystemCamelConstants.MF_RETRIEVE_PP_ORDER_V2_CAMEL_ROUTE_ID;
import static de.metas.camel.externalsystems.leichundmehl.to_leichundmehl.file.SendToFileRouteBuilder.SEND_TO_FILE_ROUTE_ID;
import static de.metas.camel.externalsystems.leichundmehl.to_leichundmehl.networking.tcp.SendToTCPRouteBuilder.SEND_TO_TCP_ROUTE_ID;
import static de.metas.camel.externalsystems.leichundmehl.to_leichundmehl.pporder.LeichUndMehlExportPPOrderRouteBuilder.EXPORT_PPORDER_ROUTE_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LeichUndMehlExportPPOrderRouteBuilderTest extends CamelTestSupport
{
	private static final String MOCK_RETRIEVE_PP_ORDER_ENDPOINT = "mock:RetrievePPOrderEndpoint";
	private static final String MOCK_RETRIEVE_PRODUCT_INFO_ENDPOINT = "mock:RetrieveProductInfoEndpoint";
	private static final String MOCK_INVOKE_PROCESS_ENDPOINT = "mock:InvokeProcessEndpoint";
	private static final String MOCK_TCP_ENDPOINT = "mock:TCPEndpoint";
	private static final String MOCK_FILE_ENDPOINT = "mock:FileEndpoint";
	private static final String MOCK_LOG_MESSAGE_ENDPOINT = "mock:LogMessageEndpoint";
	private static final String MOCK_ATTACHMENT_ENDPOINT = "mock:AttachmentEndpoint";

	private static final String JSON_EXTERNAL_SYSTEM_REQUEST_PLU_FILE_EXPORT_AUDIT_DISABLED = "pluFileExportAuditDisabled/0_JsonExternalSystemRequest.json";

	private static final String JSON_EXTERNAL_SYSTEM_REQUEST = "0_JsonExternalSystemRequest.json";
	private static final String JSON_MANUFACTURING_ORDER_RESPONSE = "10_JsonResponseManufacturingOrder.json";
	private static final String JSON_RETRIEVE_PRODUCT_CAMEL_REQUEST = "20_RetrieveProductCamelRequest.json";
	private static final String JSON_PRODUCT_RESPONSE = "20_JsonProduct.json";

	private static final String JSON_DISPATCH_MESSAGE_REQUEST = "30_DispatchMessageRequest.json";
	private static final String JSON_LOG_MESSAGE_REQUEST = "40_LogMessageRequest.json";
	private static final String JSON_ATTACHMENT_REQUEST = "50_JsonAttachmentRequest.json";

	private static final String JSON_EXTERNAL_SYSTEM_REQUEST_CUSTOM_QUERY_PROCESS = "customQueryProcess/0_JsonExternalSystemRequest.json";
	private static final String JSON_CUSTOM_PROCESS_REQUEST = "customQueryProcess/25_CustomQueryRequest.json";
	private static final String JSON_CUSTOM_PROCESS_RESPONSE = "customQueryProcess/26_CustomQueryResponse.json";
	private static final String JSON_DISPATCH_REQUEST_CUSTOM_QUERY_PROCESS = "customQueryProcess/30_DispatchMessageRequest.json";
	private static final String JSON_LOG_MESSAGE_REQUEST_CUSTOM_QUERY_PROCESS = "customQueryProcess/40_LogMessageRequest.json";
	private static final String JSON_ATTACHMENT_REQUEST_CUSTOM_QUERY_PROCESS = "customQueryProcess/50_JsonAttachmentRequest.json";

	private static final String JSON_EXTERNAL_SYSTEM_REQUEST_STORE_PLU_FILE_TO_DISK = "storeResultingPluFileToDisk/0_JsonExternalSystemRequest.json";
	private static final String JSON_DISPATCH_MESSAGE_REQUEST_STORE_PLU_FILE_TO_DISK = "storeResultingPluFileToDisk/30_DispatchMessageRequest.json";

	private static final Integer ppOrderMetasfreshId = 11111;

	private static final ObjectMapper objectMapper = JsonObjectMapperHolder.sharedJsonObjectMapper();

	@Override
	public boolean isUseAdviceWith()
	{
		return true;
	}

	@Override
	protected RouteBuilder createRouteBuilder()
	{
		final ProcessLogger processLogger = Mockito.mock(ProcessLogger.class);
		return new LeichUndMehlExportPPOrderRouteBuilder(processLogger);
	}

	@Override
	protected Properties useOverridePropertiesWithPropertiesComponent()
	{
		final Properties properties = new Properties();
		try
		{
			properties.load(LeichUndMehlExportPPOrderRouteBuilderTest.class.getClassLoader().getResourceAsStream("application.properties"));
			return properties;
		}
		catch (final IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Test
	public void happyFlow_withPluFileExportAuditEnabled() throws Exception
	{
		final MockRetrievePPOrderProcessor mockRetrievePPOrderProcessor = new MockRetrievePPOrderProcessor();
		final MockRetrieveProductInfoProcessor mockRetrieveProductInfoProcessor = new MockRetrieveProductInfoProcessor();
		final MockCustomQueryProcessor mockCustomQueryProcessor = new MockCustomQueryProcessor();
		final MockTCPProcessor mockTCPProcessor = new MockTCPProcessor();
		final MockFileProcessor mockFileProcessor = new MockFileProcessor();
		final MockLogMessageRequestProcessor mockLogMessageRequestProcessor = new MockLogMessageRequestProcessor();
		final MockJsonAttachmentRequestProcessor mockJsonAttachmentRequestProcessor = new MockJsonAttachmentRequestProcessor();

		prepareRouteForTesting(mockRetrievePPOrderProcessor,
							   mockRetrieveProductInfoProcessor,
							   mockCustomQueryProcessor,
							   mockTCPProcessor,
							   mockFileProcessor,
							   mockLogMessageRequestProcessor,
							   mockJsonAttachmentRequestProcessor);

		context.start();

		final MockEndpoint retrievePPOrderMockEndpoint = getMockEndpoint(MOCK_RETRIEVE_PP_ORDER_ENDPOINT);
		retrievePPOrderMockEndpoint.expectedBodiesReceived(ppOrderMetasfreshId);

		final InputStream expectedRetrieveProductCamelRequestIS = this.getClass().getResourceAsStream(JSON_RETRIEVE_PRODUCT_CAMEL_REQUEST);
		final MockEndpoint retrieveProductInfoMockEndpoint = getMockEndpoint(MOCK_RETRIEVE_PRODUCT_INFO_ENDPOINT);
		retrieveProductInfoMockEndpoint.expectedBodiesReceived(objectMapper.readValue(expectedRetrieveProductCamelRequestIS, RetrieveProductCamelRequest.class));

		final InputStream expectedLogMessageRequestIS = this.getClass().getResourceAsStream(JSON_LOG_MESSAGE_REQUEST);
		final MockEndpoint logMessageRequestMockEndpoint = getMockEndpoint(MOCK_LOG_MESSAGE_ENDPOINT);
		logMessageRequestMockEndpoint.expectedBodiesReceived(objectMapper.readValue(expectedLogMessageRequestIS, LogMessageRequest.class));

		//input request
		final InputStream invokeExternalSystemRequestIS = this.getClass().getResourceAsStream(JSON_EXTERNAL_SYSTEM_REQUEST);
		final JsonExternalSystemRequest invokeExternalSystemRequest = objectMapper.readValue(invokeExternalSystemRequestIS, JsonExternalSystemRequest.class);

		//when
		template.sendBody("direct:" + EXPORT_PPORDER_ROUTE_ID, invokeExternalSystemRequest);

		//then
		assertThat(mockTCPProcessor.called).isEqualTo(1);
		assertThat(mockRetrievePPOrderProcessor.called).isEqualTo(1);
		assertThat(mockRetrieveProductInfoProcessor.called).isEqualTo(1);
		assertThat(mockCustomQueryProcessor.called).isEqualTo(0);
		assertThat(mockLogMessageRequestProcessor.called).isEqualTo(1);
		assertThat(mockJsonAttachmentRequestProcessor.called).isEqualTo(1);
		assertMockEndpointsSatisfied();

		//validate DispatchMessageRequest
		final InputStream expectedDispatchMessageRequestIS = this.getClass().getResourceAsStream(JSON_DISPATCH_MESSAGE_REQUEST);
		final DispatchMessageRequest expectedDispatchMessageRequest = objectMapper.readValue(expectedDispatchMessageRequestIS, DispatchMessageRequest.class);
		assertThat(mockTCPProcessor.actualRequest.getDestinationDetails().getConnectionDetailsNotNull()).isEqualTo(expectedDispatchMessageRequest.getDestinationDetails().getConnectionDetailsNotNull());
		assertThat(mockTCPProcessor.actualRequest.getPayload().replaceAll("\\s+", ""))
				.isEqualTo(expectedDispatchMessageRequest.getPayload().replaceAll("\\s+", ""));

		//validate JsonAttachmentRequest
		final InputStream expectedJsonAttachmentRequestIS = this.getClass().getResourceAsStream(JSON_ATTACHMENT_REQUEST);
		final JsonAttachmentRequest expectedJsonAttachmentRequest = objectMapper.readValue(expectedJsonAttachmentRequestIS, JsonAttachmentRequest.class);

		final JsonAttachmentRequest actualJsonAttachmentRequest = mockJsonAttachmentRequestProcessor.jsonAttachmentRequest;

		assertThat(actualJsonAttachmentRequest.getOrgCode().equals(expectedJsonAttachmentRequest.getOrgCode())).isTrue();
		assertThat(actualJsonAttachmentRequest.getTargets().equals(expectedJsonAttachmentRequest.getTargets())).isTrue();

		final JsonAttachment expectedJsonAttachment = expectedJsonAttachmentRequest.getAttachment();
		final JsonAttachment actualJsonAttachment = actualJsonAttachmentRequest.getAttachment();

		final byte[] expectedData = Base64.getDecoder().decode(expectedJsonAttachment.getData().getBytes());
		final String expectedDataString = new String(expectedData, Charset.forName("windows-1252"));

		final byte[] actualData = Base64.getDecoder().decode(actualJsonAttachment.getData().getBytes());
		final String actualDataString = new String(actualData, Charset.forName("windows-1252"));

		assertThat(actualJsonAttachment.getFileName().equals(expectedJsonAttachment.getFileName())).isTrue();
		assertThat(actualJsonAttachment.getType().equals(expectedJsonAttachment.getType())).isTrue();
		assertThat(actualDataString.replaceAll("\\s+", ""))
				.isEqualTo(expectedDataString.replaceAll("\\s+", ""));
	}

	@Test
	public void happyFlow_withPluFileExportAuditNotEnabled() throws Exception
	{
		final MockRetrievePPOrderProcessor mockRetrievePPOrderProcessor = new MockRetrievePPOrderProcessor();
		final MockRetrieveProductInfoProcessor mockRetrieveProductInfoProcessor = new MockRetrieveProductInfoProcessor();
		final MockCustomQueryProcessor mockCustomQueryProcessor = new MockCustomQueryProcessor();
		final MockTCPProcessor mockTCPProcessor = new MockTCPProcessor();
		final MockFileProcessor mockFileProcessor = new MockFileProcessor();
		final MockLogMessageRequestProcessor mockLogMessageRequestProcessor = new MockLogMessageRequestProcessor();
		final MockJsonAttachmentRequestProcessor mockJsonAttachmentRequestProcessor = new MockJsonAttachmentRequestProcessor();

		prepareRouteForTesting(mockRetrievePPOrderProcessor,
							   mockRetrieveProductInfoProcessor,
							   mockCustomQueryProcessor,
							   mockTCPProcessor,
							   mockFileProcessor,
							   mockLogMessageRequestProcessor,
							   mockJsonAttachmentRequestProcessor);

		context.start();

		final MockEndpoint retrievePPOrderMockEndpoint = getMockEndpoint(MOCK_RETRIEVE_PP_ORDER_ENDPOINT);
		retrievePPOrderMockEndpoint.expectedBodiesReceived(ppOrderMetasfreshId);

		final InputStream expectedRetrieveProductCamelRequestIS = this.getClass().getResourceAsStream(JSON_RETRIEVE_PRODUCT_CAMEL_REQUEST);
		final MockEndpoint retrieveProductInfoMockEndpoint = getMockEndpoint(MOCK_RETRIEVE_PRODUCT_INFO_ENDPOINT);
		retrieveProductInfoMockEndpoint.expectedBodiesReceived(objectMapper.readValue(expectedRetrieveProductCamelRequestIS, RetrieveProductCamelRequest.class));

		//input request
		final InputStream invokeExternalSystemRequestIS = this.getClass().getResourceAsStream(JSON_EXTERNAL_SYSTEM_REQUEST_PLU_FILE_EXPORT_AUDIT_DISABLED);
		final JsonExternalSystemRequest invokeExternalSystemRequest = objectMapper.readValue(invokeExternalSystemRequestIS, JsonExternalSystemRequest.class);

		//when
		template.sendBody("direct:" + EXPORT_PPORDER_ROUTE_ID, invokeExternalSystemRequest);

		//then
		assertThat(mockTCPProcessor.called).isEqualTo(1);
		assertThat(mockRetrievePPOrderProcessor.called).isEqualTo(1);
		assertThat(mockRetrieveProductInfoProcessor.called).isEqualTo(1);
		assertThat(mockCustomQueryProcessor.called).isEqualTo(0);
		assertThat(mockLogMessageRequestProcessor.called).isEqualTo(0);
		assertThat(mockJsonAttachmentRequestProcessor.called).isEqualTo(0);
		assertMockEndpointsSatisfied();

		//validate DispatchMessageRequest
		final InputStream expectedDispatchMessageRequestIS = this.getClass().getResourceAsStream(JSON_DISPATCH_MESSAGE_REQUEST);
		final DispatchMessageRequest expectedDispatchMessageRequest = objectMapper.readValue(expectedDispatchMessageRequestIS, DispatchMessageRequest.class);
		assertThat(mockTCPProcessor.actualRequest.getDestinationDetails().getConnectionDetailsNotNull()).isEqualTo(expectedDispatchMessageRequest.getDestinationDetails().getConnectionDetailsNotNull());
		assertThat(mockTCPProcessor.actualRequest.getPayload().replaceAll("\\s+", ""))
				.isEqualTo(expectedDispatchMessageRequest.getPayload().replaceAll("\\s+", ""));
	}

	/**
	 * The request prompts camel to *also* make a call to a custom process and incorporate the result into the PLU-file.
	 */
	@Test
	public void happyFlow_withCustomQueryProcess() throws Exception
	{
		final MockRetrievePPOrderProcessor mockRetrievePPOrderProcessor = new MockRetrievePPOrderProcessor();
		final MockRetrieveProductInfoProcessor mockRetrieveProductInfoProcessor = new MockRetrieveProductInfoProcessor();
		final MockCustomQueryProcessor mockCustomQueryProcessor = new MockCustomQueryProcessor();
		final MockTCPProcessor mockTCPProcessor = new MockTCPProcessor();
		final MockFileProcessor mockFileProcessor = new MockFileProcessor();
		final MockLogMessageRequestProcessor mockLogMessageRequestProcessor = new MockLogMessageRequestProcessor();
		final MockJsonAttachmentRequestProcessor mockJsonAttachmentRequestProcessor = new MockJsonAttachmentRequestProcessor();

		prepareRouteForTesting(mockRetrievePPOrderProcessor,
							   mockRetrieveProductInfoProcessor,
							   mockCustomQueryProcessor,
							   mockTCPProcessor,
							   mockFileProcessor,
							   mockLogMessageRequestProcessor,
							   mockJsonAttachmentRequestProcessor);

		context.start();

		final MockEndpoint retrievePPOrderMockEndpoint = getMockEndpoint(MOCK_RETRIEVE_PP_ORDER_ENDPOINT);
		retrievePPOrderMockEndpoint.expectedBodiesReceived(ppOrderMetasfreshId);

		final InputStream expectedRetrieveProductCamelRequestIS = this.getClass().getResourceAsStream(JSON_RETRIEVE_PRODUCT_CAMEL_REQUEST);
		final MockEndpoint retrieveProductInfoMockEndpoint = getMockEndpoint(MOCK_RETRIEVE_PRODUCT_INFO_ENDPOINT);
		retrieveProductInfoMockEndpoint.expectedBodiesReceived(objectMapper.readValue(expectedRetrieveProductCamelRequestIS, RetrieveProductCamelRequest.class));

		final InputStream customProcessRequestIS = this.getClass().getResourceAsStream(JSON_CUSTOM_PROCESS_REQUEST);
		final MockEndpoint customProcessRequestMockEndpoint = getMockEndpoint(MOCK_INVOKE_PROCESS_ENDPOINT);
		customProcessRequestMockEndpoint.expectedBodiesReceived(objectMapper.readValue(customProcessRequestIS, JsonAdProcessRequest.class));

		final InputStream expectedLogMessageRequestIS = this.getClass().getResourceAsStream(JSON_LOG_MESSAGE_REQUEST_CUSTOM_QUERY_PROCESS);
		final MockEndpoint logMessageRequestMockEndpoint = getMockEndpoint(MOCK_LOG_MESSAGE_ENDPOINT);
		logMessageRequestMockEndpoint.expectedBodiesReceived(objectMapper.readValue(expectedLogMessageRequestIS, LogMessageRequest.class));

		//input request
		final InputStream invokeExternalSystemRequestIS = this.getClass().getResourceAsStream(JSON_EXTERNAL_SYSTEM_REQUEST_CUSTOM_QUERY_PROCESS);
		final JsonExternalSystemRequest invokeExternalSystemRequest = objectMapper.readValue(invokeExternalSystemRequestIS, JsonExternalSystemRequest.class);

		//when
		template.sendBody("direct:" + EXPORT_PPORDER_ROUTE_ID, invokeExternalSystemRequest);

		//then
		assertThat(mockTCPProcessor.called).isEqualTo(1);
		assertThat(mockRetrievePPOrderProcessor.called).isEqualTo(1);
		assertThat(mockRetrieveProductInfoProcessor.called).isEqualTo(1);
		assertThat(mockCustomQueryProcessor.called).isEqualTo(1);
		assertThat(mockLogMessageRequestProcessor.called).isEqualTo(1);
		assertThat(mockJsonAttachmentRequestProcessor.called).isEqualTo(1);
		assertMockEndpointsSatisfied();

		//validate DispatchMessageRequest
		final InputStream expectedDispatchMessageRequestIS = this.getClass().getResourceAsStream(JSON_DISPATCH_REQUEST_CUSTOM_QUERY_PROCESS);
		final DispatchMessageRequest expectedDispatchMessageRequest = objectMapper.readValue(expectedDispatchMessageRequestIS, DispatchMessageRequest.class);
		assertThat(mockTCPProcessor.actualRequest.getDestinationDetails().getConnectionDetailsNotNull()).isEqualTo(expectedDispatchMessageRequest.getDestinationDetails().getConnectionDetailsNotNull());
		assertThat(mockTCPProcessor.actualRequest.getPayload().replaceAll("\\s+", ""))
				.isEqualTo(expectedDispatchMessageRequest.getPayload().replaceAll("\\s+", ""));

		//validate JsonAttachmentRequest
		final InputStream expectedJsonAttachmentRequestIS = this.getClass().getResourceAsStream(JSON_ATTACHMENT_REQUEST_CUSTOM_QUERY_PROCESS);
		final JsonAttachmentRequest expectedJsonAttachmentRequest = objectMapper.readValue(expectedJsonAttachmentRequestIS, JsonAttachmentRequest.class);

		final JsonAttachmentRequest actualJsonAttachmentRequest = mockJsonAttachmentRequestProcessor.jsonAttachmentRequest;

		assertThat(actualJsonAttachmentRequest.getOrgCode().equals(expectedJsonAttachmentRequest.getOrgCode())).isTrue();
		assertThat(actualJsonAttachmentRequest.getTargets().equals(expectedJsonAttachmentRequest.getTargets())).isTrue();

		final JsonAttachment expectedJsonAttachment = expectedJsonAttachmentRequest.getAttachment();
		final JsonAttachment actualJsonAttachment = actualJsonAttachmentRequest.getAttachment();

		final byte[] expectedData = Base64.getDecoder().decode(expectedJsonAttachment.getData().getBytes());
		final String expectedDataString = new String(expectedData, Charset.forName("windows-1252"));

		final byte[] actualData = Base64.getDecoder().decode(actualJsonAttachment.getData().getBytes());
		final String actualDataString = new String(actualData, Charset.forName("windows-1252"));

		assertThat(actualJsonAttachment.getFileName().equals(expectedJsonAttachment.getFileName())).isTrue();
		assertThat(actualJsonAttachment.getType().equals(expectedJsonAttachment.getType())).isTrue();
		assertThat(actualDataString.replaceAll("\\s+", ""))
				.isEqualTo(expectedDataString.replaceAll("\\s+", ""));
	}

	@Test
	public void happyFlow_store_PLU_file_to_disk() throws Exception
	{
		final MockRetrievePPOrderProcessor mockRetrievePPOrderProcessor = new MockRetrievePPOrderProcessor();
		final MockRetrieveProductInfoProcessor mockRetrieveProductInfoProcessor = new MockRetrieveProductInfoProcessor();
		final MockCustomQueryProcessor mockCustomQueryProcessor = new MockCustomQueryProcessor();
		final MockTCPProcessor mockTCPProcessor = new MockTCPProcessor();
		final MockFileProcessor mockFileProcessor = new MockFileProcessor();
		final MockLogMessageRequestProcessor mockLogMessageRequestProcessor = new MockLogMessageRequestProcessor();
		final MockJsonAttachmentRequestProcessor mockJsonAttachmentRequestProcessor = new MockJsonAttachmentRequestProcessor();

		prepareRouteForTesting(mockRetrievePPOrderProcessor,
							   mockRetrieveProductInfoProcessor,
							   mockCustomQueryProcessor,
							   mockTCPProcessor,
							   mockFileProcessor,
							   mockLogMessageRequestProcessor,
							   mockJsonAttachmentRequestProcessor);

		context.start();

		final MockEndpoint retrievePPOrderMockEndpoint = getMockEndpoint(MOCK_RETRIEVE_PP_ORDER_ENDPOINT);
		retrievePPOrderMockEndpoint.expectedBodiesReceived(ppOrderMetasfreshId);

		final InputStream expectedRetrieveProductCamelRequestIS = this.getClass().getResourceAsStream(JSON_RETRIEVE_PRODUCT_CAMEL_REQUEST);
		final MockEndpoint retrieveProductInfoMockEndpoint = getMockEndpoint(MOCK_RETRIEVE_PRODUCT_INFO_ENDPOINT);
		retrieveProductInfoMockEndpoint.expectedBodiesReceived(objectMapper.readValue(expectedRetrieveProductCamelRequestIS, RetrieveProductCamelRequest.class));

		//input request
		final InputStream invokeExternalSystemRequestIS = this.getClass().getResourceAsStream(JSON_EXTERNAL_SYSTEM_REQUEST_STORE_PLU_FILE_TO_DISK);
		final JsonExternalSystemRequest invokeExternalSystemRequest = objectMapper.readValue(invokeExternalSystemRequestIS, JsonExternalSystemRequest.class);

		//when
		template.sendBody("direct:" + EXPORT_PPORDER_ROUTE_ID, invokeExternalSystemRequest);

		//then
		final SoftAssertions softly = new SoftAssertions();
		softly.assertThat(mockTCPProcessor.called).as("mockTCPProcessor").isEqualTo(0);
		softly.assertThat(mockFileProcessor.called).as("mockFileProcessor").isEqualTo(1);
		softly.assertThat(mockRetrievePPOrderProcessor.called).as("mockRetrievePPOrderProcessor").isEqualTo(1);
		softly.assertThat(mockRetrieveProductInfoProcessor.called).as("mockRetrieveProductInfoProcessor").isEqualTo(1);
		softly.assertThat(mockCustomQueryProcessor.called).as("mockCustomQueryProcessor").isEqualTo(0);
		softly.assertThat(mockLogMessageRequestProcessor.called).as("mockLogMessageRequestProcessor").isEqualTo(1);
		softly.assertThat(mockJsonAttachmentRequestProcessor.called).as("mockJsonAttachmentRequestProcessor").isEqualTo(1);
		softly.assertAll();
		assertMockEndpointsSatisfied();

		//validate DispatchMessageRequest
		final InputStream expectedDispatchMessageRequestIS = this.getClass().getResourceAsStream(JSON_DISPATCH_MESSAGE_REQUEST_STORE_PLU_FILE_TO_DISK);
		final DispatchMessageRequest expectedDispatchMessageRequest = objectMapper.readValue(expectedDispatchMessageRequestIS, DispatchMessageRequest.class);
		assertThat(mockFileProcessor.actualRequest.getDestinationDetails().getPluFileServerFolderNotBlank()).isEqualTo(expectedDispatchMessageRequest.getDestinationDetails().getPluFileServerFolderNotBlank());
		assertThat(mockFileProcessor.actualRequest.getPayload().replaceAll("\\s+", ""))
				.isEqualTo(expectedDispatchMessageRequest.getPayload().replaceAll("\\s+", ""));
	}

	private void prepareRouteForTesting(
			@NonNull final MockRetrievePPOrderProcessor mockRetrievePPOrderProcessor,
			@NonNull final MockRetrieveProductInfoProcessor mockRetrieveProductInfoProcessor,
			@NonNull final MockCustomQueryProcessor mockCustomQueryProcessor,
			@NonNull final MockTCPProcessor mockTCPProcessor,
			@NonNull final MockFileProcessor mockFileProcessor,
			@NonNull final MockLogMessageRequestProcessor mockLogMessageRequestProcessor,
			@NonNull final MockJsonAttachmentRequestProcessor mockJsonAttachmentRequestProcessor) throws Exception
	{
		AdviceWith.adviceWith(context, EXPORT_PPORDER_ROUTE_ID,
							  advice -> {
								  advice.interceptSendToEndpoint("direct:" + MF_RETRIEVE_PP_ORDER_V2_CAMEL_ROUTE_ID)
										  .skipSendToOriginalEndpoint()
										  .to(MOCK_RETRIEVE_PP_ORDER_ENDPOINT)
										  .process(mockRetrievePPOrderProcessor);

								  advice.interceptSendToEndpoint("direct:" + MF_RETRIEVE_MATERIAL_PRODUCT_INFO_V2_CAMEL_ROUTE_ID)
										  .skipSendToOriginalEndpoint()
										  .to(MOCK_RETRIEVE_PRODUCT_INFO_ENDPOINT)
										  .process(mockRetrieveProductInfoProcessor);

								  advice.interceptSendToEndpoint("direct:" + MF_AD_Process_ROUTE_ID)
										  .skipSendToOriginalEndpoint()
										  .to(MOCK_INVOKE_PROCESS_ENDPOINT)
										  .process(mockCustomQueryProcessor);

								  advice.interceptSendToEndpoint("direct:" + SEND_TO_TCP_ROUTE_ID)
										  .skipSendToOriginalEndpoint()
										  .to(MOCK_TCP_ENDPOINT)
										  .process(mockTCPProcessor);

								  advice.interceptSendToEndpoint("direct:" + SEND_TO_FILE_ROUTE_ID)
										  .skipSendToOriginalEndpoint()
										  .to(MOCK_FILE_ENDPOINT)
										  .process(mockFileProcessor);

								  advice.interceptSendToEndpoint("direct:" + MF_LOG_MESSAGE_ROUTE_ID)
										  .skipSendToOriginalEndpoint()
										  .to(MOCK_LOG_MESSAGE_ENDPOINT)
										  .process(mockLogMessageRequestProcessor);

								  advice.interceptSendToEndpoint("direct:" + MF_ATTACHMENT_ROUTE_ID)
										  .skipSendToOriginalEndpoint()
										  .to(MOCK_ATTACHMENT_ENDPOINT)
										  .process(mockJsonAttachmentRequestProcessor);
							  });
	}

	private static class MockRetrievePPOrderProcessor implements Processor
	{
		private int called = 0;

		@Override
		public void process(final Exchange exchange)
		{
			called++;
			final InputStream expectedJsonResponseManufacturingOrderIS = this.getClass().getResourceAsStream(JSON_MANUFACTURING_ORDER_RESPONSE);
			exchange.getIn().setBody(expectedJsonResponseManufacturingOrderIS);
		}
	}

	private static class MockRetrieveProductInfoProcessor implements Processor
	{
		private int called = 0;

		@Override
		public void process(final Exchange exchange)
		{
			called++;
			final InputStream expectedJsonProductIS = this.getClass().getResourceAsStream(JSON_PRODUCT_RESPONSE);
			exchange.getIn().setBody(expectedJsonProductIS);
		}
	}

	private static class MockCustomQueryProcessor implements Processor
	{
		private int called = 0;

		@Override
		public void process(final Exchange exchange)
		{
			called++;
			final InputStream expectedJsonResponseManufacturingOrderIS = this.getClass().getResourceAsStream(JSON_CUSTOM_PROCESS_RESPONSE);
			exchange.getIn().setBody(expectedJsonResponseManufacturingOrderIS);
		}
	}

	private static class MockTCPProcessor implements Processor
	{
		private int called = 0;
		private DispatchMessageRequest actualRequest;

		@Override
		public void process(@NonNull final Exchange exchange)
		{
			actualRequest = exchange.getIn().getBody(DispatchMessageRequest.class);

			called++;
		}
	}

	private static class MockFileProcessor implements Processor
	{
		private int called = 0;
		private DispatchMessageRequest actualRequest;

		@Override
		public void process(@NonNull final Exchange exchange)
		{
			actualRequest = exchange.getIn().getBody(DispatchMessageRequest.class);

			called++;
		}
	}

	private static class MockLogMessageRequestProcessor implements Processor
	{
		private int called = 0;

		@Override
		public void process(@NonNull final Exchange exchange)
		{
			called++;
		}
	}

	private static class MockJsonAttachmentRequestProcessor implements Processor
	{
		private int called = 0;
		private JsonAttachmentRequest jsonAttachmentRequest;

		@Override
		public void process(@NonNull final Exchange exchange)
		{
			jsonAttachmentRequest = exchange.getIn().getBody(JsonAttachmentRequest.class);

			called++;
		}
	}
}
