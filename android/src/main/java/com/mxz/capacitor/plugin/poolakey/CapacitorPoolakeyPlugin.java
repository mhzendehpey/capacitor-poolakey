package com.mxz.capacitor.plugin.poolakey;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorPoolakey")
public class CapacitorPoolakeyPlugin extends Plugin {

    private final CapacitorPoolakey implementation = new CapacitorPoolakey();

    @PluginMethod
    public final void connectPayment(PluginCall call) {
        this.implementation.connectPayment(call, this.getContext());
    }

    @PluginMethod
    public final void disconnectPayment(PluginCall call) {
        this.implementation.disconnectPayment(call);
    }

    @PluginMethod
    public final void purchaseProduct(PluginCall call) {
        this.implementation.purchaseProduct(call, getActivity());
    }

    @PluginMethod
    public final void subscribeProduct(PluginCall call) {
        this.implementation.subscribeProduct(call, getActivity());
    }

    @PluginMethod
    public final void consumeProduct(PluginCall call) {
        this.implementation.consumePurchase(call);
    }

    @PluginMethod
    public final void getPurchasedProducts(PluginCall call) {
        this.implementation.getPurchasedProducts(call);
    }

    @PluginMethod
    public final void getSubscribedProducts(PluginCall call) {
        this.implementation.getSubscribedProducts(call);
    }

    @PluginMethod
    public final void queryPurchaseProduct(PluginCall call) {
        this.implementation.queryPurchaseProduct(call);
    }

    @PluginMethod
    public final void querySubscribeProduct(PluginCall call) {
        this.implementation.querySubscribeProduct(call);
    }

    @PluginMethod
    public final void getInAppSkuDetails(PluginCall call) {
        this.implementation.getInAppSkuDetails(call);
    }

    @PluginMethod
    public final void getSubscriptionSkuDetails(PluginCall call) {
        this.implementation.getSubscriptionSkuDetails(call);
    }
}
