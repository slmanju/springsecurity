package com.manjula.securityjwt.controller;

public class ResourceNotFoundException extends RuntimeException {

    private Object resourceId;

    public ResourceNotFoundException(Object resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

    public static ResourceNotFoundException instance(Object resourceId, String message) {
        return new ResourceNotFoundException(resourceId, message);
    }

}
