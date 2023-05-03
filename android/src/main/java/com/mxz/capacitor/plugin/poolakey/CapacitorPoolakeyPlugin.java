package com.mxz.capacitor.plugin.poolakey;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.Objects;

@CapacitorPlugin(name = "CapacitorPoolakey")
public class CapacitorPoolakeyPlugin extends Plugin {
    private final CapacitorPoolakey implementation = new CapacitorPoolakey();

    @PluginMethod
    public final void echo(PluginCall call) {
        String value = call.getString("value");
        JSObject ret = new JSObject();
        ret.put("value", this.implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public final void connectPayment(PluginCall call) {
        String rsaPublicKey = call.getString("rsaPublicKey");
        this.implementation.connectPayment(this.getContext(), rsaPublicKey);
    }

    @PluginMethod
    public final void disconnectPayment(PluginCall call) {
        this.implementation.disconnectPayment();
    }

    @PluginMethod
    public final void purchaseProduct(PluginCall call) {
        String productId = Objects.requireNonNull(call.getString("productId"));
        String payload = call.getString("payload");
        String dynamicPriceToken = call.getString("dynamicPriceToken");

        this.implementation.purchaseProduct(
                getActivity(),
                productId,
                payload,
                dynamicPriceToken
        );
    }

    @PluginMethod
    public final void subscribeProduct(PluginCall call) {
        String productId = Objects.requireNonNull(call.getString("productId"));
        String payload = call.getString("payload");
        String dynamicPriceToken = call.getString("dynamicPriceToken");

        this.implementation.subscribeProduct(
                getActivity(),
                productId,
                payload,
                dynamicPriceToken
        );
    }

    @PluginMethod
    public final void consumeProduct(PluginCall call) {
        String purchaseToken = Objects.requireNonNull(call.getString("purchaseToken"));
        this.implementation.consumePurchase(purchaseToken);
    }

}