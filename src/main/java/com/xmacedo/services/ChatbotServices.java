package com.xmacedo.services;

import com.xmacedo.components.LlamaModelComponent;
import com.xmacedo.components.PromptComponent;
import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.LlamaOutput;
import de.kherud.llama.args.MiroStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChatbotServices {

    @Value("${llamacpp.temperature}")
    private String modelTemperature;

    @Value("${llamacpp.topp}")
    private Integer modelTopP;

    @Autowired
    PromptComponent promptComponent;

    @Autowired
    LlamaModelComponent modelComponent;

    final Logger log1 = LoggerFactory.getLogger(this.getClass());

    public void generateResponse(String question) {
        log1.info("Receive question:{}", question);
        LlamaModel modelLlama = modelComponent.getModelLlm();

        String prompt = promptComponent.getPromptContent()
                .replace("{question}", question);
        String listAntiprompt = "</s>,<|im_end|>,User:";
        log1.info("Prompt:{}", prompt);
        InferenceParameters inferParams = new InferenceParameters(prompt)
                .setTemperature(new Float(modelTemperature))
                .setTopP(modelTopP)
                .setFrequencyPenalty(0.2F)
                .setMiroStat(MiroStat.V2)
                .setStopStrings(listAntiprompt.split("[,]"));

        for (LlamaOutput output : modelLlama.generate(inferParams)) {
            System.out.print(output.toString());
        }
        //Add line separator
        System.out.print(System.lineSeparator());
    }
}
