package im.dnn.SpigotRestAPI.Services;

import im.dnn.SpigotRestAPI.Models.wResponse;

abstract public class WebResponseService {
    public WebResponseService () {}

    protected wResponse success () {
        return success(null);
    }

    protected wResponse success (Object data) {
        return new wResponse("OK", data);
    }

    protected wResponse failure () {
        return failure(null);
    }

    protected wResponse failure (Object data) {
        return new wResponse("Fail", data);
    }

    protected wResponse failIfNull (Object data) {
        if (data == null) {
            return failure();
        }
        return success(data);
    }
}
