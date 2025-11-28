package com.dairy.Dto;

public class ResponseDTO {

    public Object data;
    public String message;
    public Boolean success;
    public Long status;
//    public Long refNo;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

//    public Long geRefNo() {
//        return refNo;
//    }
//
//    public void setRefNo(Long refNo) {
//        this.refNo = refNo;
//    }

}
