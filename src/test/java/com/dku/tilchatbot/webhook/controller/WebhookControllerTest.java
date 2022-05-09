package com.dku.tilchatbot.webhook.controller;

import com.dku.tilchatbot.record.service.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class WebhookControllerTest {

    private static final String url = "/api/v1/webhook";

    @Mock
    private RecordService recordService;

    @InjectMocks
    private WebhookController webhookController;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(webhookController)
                .build();
    }

    @Nested
    @DisplayName("parseWebhook 메서드는")
    class Describe_parseWebhook {

        @Nested
        @DisplayName("IntentType이 존재하지 않는 경우")
        class Context_withoutIntentType {

            @Test
            @DisplayName("badRequest를 응답한다")
            void It_responseBadRequest() throws Exception {
                final var file = new ClassPathResource("requestSample/requestWithoutIntent.json").getFile();
                final var payload = Files.readString(file.toPath());

                final var request = post(url)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON);

                final var resultActions = mockMvc.perform(request);
                resultActions.andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("IntentType이 유효하지 않은 값인 경우")
        class Context_with_InvalidIntentType {

            @Test
            @DisplayName("badRequest를 응답한다")
            void It_responseBadRequest() throws Exception {
                final var file = new ClassPathResource("requestSample/requestWithInvalidIntent.json").getFile();
                final var payload = Files.readString(file.toPath());

                final var request = post(url)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON);

                final var resultActions = mockMvc.perform(request);
                resultActions.andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("Category가 존재하지 않는경우")
        class Context_withoutCategory {

            @Test
            @DisplayName("badRequest를 응답한다")
            void It_responseBadRequest() throws Exception {
                final var file = new ClassPathResource("requestSample/saveRequestWithoutCategory.json").getFile();
                final var payload = Files.readString(file.toPath());

                final var request = post(url)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON);

                final var resultActions = mockMvc.perform(request);
                resultActions.andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("Category가 유효하지 않은 값인 경우")
        class Context_with_InvalidCategory {

            @Test
            @DisplayName("badRequest를 응답한다")
            void It_responseBadRequest() throws Exception {
                final var file = new ClassPathResource("requestSample/saveRequestWithInvalidCategory.json").getFile();
                final var payload = Files.readString(file.toPath());

                final var request = post(url)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON);

                final var resultActions = mockMvc.perform(request);
                resultActions.andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("startTime이 유효하지 않은 경우")
        class Context_withInvalidStartTime {

            @Test
            @DisplayName("badRequest를 응답한다")
            void It_responseBadRequest() throws Exception {
                final var file = new ClassPathResource("requestSample/saveRequestWithInvalidStartTime.json").getFile();
                final var payload = Files.readString(file.toPath());

                final var request = post(url)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON);

                final var resultActions = mockMvc.perform(request);
                resultActions.andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("endTime이 유효하지 않은 경우")
        class Context_withInvalidEndTime {

            @Test
            @DisplayName("badRequest를 응답한다")
            void It_responseBadRequest() throws Exception {
                final var file = new ClassPathResource("requestSample/saveRequestWithInvalidEndTime.json").getFile();
                final var payload = Files.readString(file.toPath());

                final var request = post(url)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON);

                final var resultActions = mockMvc.perform(request);
                resultActions.andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("IntentType이 SAVE_RECORD 이고, startTime, endTime, category가 유효한 값인 경우")
        class Context_with_Save {

            @Test
            @DisplayName("ok 응답을 반환한다")
            void It_responseOk() throws Exception {
                final var file = new ClassPathResource("requestSample/saveRequest.json").getFile();
                final var payload = Files.readString(file.toPath());

                final var request = post(url)
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON);

                final var resultActions = mockMvc.perform(request);
                resultActions.andExpect(status().isOk());
                verify(recordService).saveRecord(any());
            }
        }
    }
}