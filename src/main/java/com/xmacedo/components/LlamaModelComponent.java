package com.xmacedo.components;

import de.kherud.llama.LlamaModel;
import de.kherud.llama.ModelParameters;
import de.kherud.llama.args.LogFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LlamaModelComponent {
    @Value("${llamacpp.model}")
    private String modelPath;

    @Value("${llamacpp.thread.cpu}")
    private Integer cpuCount;

    @Value("${llamacpp.number.context}")
    private Integer nCtx;

    LlamaModel modelLlm;

    @PostConstruct
    public void init() {

        //Nullify log
        LlamaModel.setLogger(LogFormat.TEXT, (level, message) -> {

        });

        //Prepare model params
        ModelParameters params = new ModelParameters().setNThreads(cpuCount)
                .setNCtx(nCtx)
                .setModelFilePath(modelPath);

        modelLlm = new LlamaModel(params);
    }

    public LlamaModel getModelLlm() {
        return modelLlm;
    }

    @PreDestroy
    public void beforeExit() {
        modelLlm.close();
    }
}
