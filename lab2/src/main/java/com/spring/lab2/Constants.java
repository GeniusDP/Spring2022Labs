package com.spring.lab2;

public class Constants {
    public static class TodoController {
        public static final String TAG = "Lots REST Controller";
        public static final String TAG_DESCRIPTION = "Controller that provides CRUD functionality for lots";
        public static final String GET_LOT_BY_ID_SUMMARY = "get lot by id";
        public static final String GET_LOT_BY_ID_DESCRIPTION = "Retrieving a certain lot from a database by id";
        public static final String GET_ALL_LOTS_SUMMARY = "get all lots";
        public static final String GET_ALL_LOTS_DESCRIPTION = "Retrieving all lots is system";
        public static final String GET_PAGINATED_LOTS_LIST_SUMMARY = "Get paginated todos";
        public static final String GET_PAGINATED_LOTS_LIST_DESCRIPTION = "Get paginated todos using page number and page size";
        public static final String SAVE_LOT_ITEM_SUMMARY = "create new lot";
        public static final String SAVE_LOT_ITEM_DESCRIPTION = "Save new lot using passed params";
        public static final String UPDATE_LOT_BY_ID_SUMMARY = "update lot by id";
        public static final String UPDATE_LOT_BY_ID_DESCRIPTION = "Updating lot means that lot will be replaced with new one";
        public static final String DELETE_LOT_BY_ID_SUMMARY = "Delete todo by id";
        public static final String DELETE_LOT_BY_ID_DESCRIPTION = "If no such todo than message will be provided.";

    }

    public static class Informer {
        public static final String HTTP_STATUS_OK = "200";
        public static final String HTTP_STATUS_NO_CONTENT = "204";
        public static final String HTTP_STATUS_CREATED = "201";
        public static final String HTTP_STATUS_NOT_FOUND = "404";
    }
}
