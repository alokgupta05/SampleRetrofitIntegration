package com.sample.retro.network;

import retrofit.RestAdapter;

public class ServiceGenerator {

    private ServiceGenerator() {

    }

    public static <S> S createService(Class<S> serviceClass,
                                      AdapterProvider adapterProvider) {

        RestAdapter adapter = adapterProvider.getRestAdapter();
        if (adapter != null)
            return adapter.create(serviceClass);
        else
            return null;
    }

}
