// IRemoteService.aidl
package com.senior.servicelib;

import com.senior.servicelib.IParticipateCallback;

interface IRemoteService {
    int someOperate(int a, int b);

    void join(IBinder token, String name);
    void leave(IBinder token);
    List<String> getParticipators();

    void registerParticipateCallback(IParticipateCallback cb);
    void unregisterParticipateCallback(IParticipateCallback cb);
}

