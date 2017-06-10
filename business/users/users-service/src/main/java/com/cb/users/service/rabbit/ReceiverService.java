package com.cb.users.service.rabbit;

/**
 * Created by oo on 17-6-10.
 */
public interface ReceiverService {
    void process(String message);
}
