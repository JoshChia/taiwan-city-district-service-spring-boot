package com.example.taiwancityservice.exception;

import com.example.taiwancityservice.enums.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ECIPException extends RuntimeException{
    ReturnCode returnCode;
    String msg;

    public ECIPException(ReturnCode returnCode){
        this.returnCode = returnCode;
    }

    public ECIPException(String msg){
        this.msg = msg;
    }

}
