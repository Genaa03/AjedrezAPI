package com.example.tpajedrezapi.dtos.common;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public class BooleanResponse {
    private boolean success;

    public boolean getSuccess(){
        return this.success;
    }
    public static BooleanResponse Ok(){
        return new BooleanResponse(true);
    }
    public static BooleanResponse Fail(){
        return new BooleanResponse(false);
    }
}
