package com.naresh.learning.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.naresh.learning.domain.NumberToWords;
import com.naresh.learning.dto.ConversionRequest;
import com.naresh.learning.dto.ConversionResponse;

public class ConversionRequestHandler implements RequestHandler<ConversionRequest, ConversionResponse> {
    @Override
    public ConversionResponse handleRequest(ConversionRequest request, Context context) {
        return new ConversionResponse(NumberToWords.transform(request.getInputNumber()));
    }
}
