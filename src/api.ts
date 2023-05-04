import type {CapacitorPoolakeyPlugin, PoolakeyBridge, PurchaseInfo, PurchaseInfoList, SkuDetails} from "./definitions";

export class PoolakeyAPI implements PoolakeyBridge {
    plugin: CapacitorPoolakeyPlugin

    constructor(plugin: CapacitorPoolakeyPlugin) {
        this.plugin = plugin;
    }

    connectPayment(rsaPublicKey: string): Promise<void> {
        return this.plugin.connectPayment({rsaPublicKey: rsaPublicKey})
    }

    consumeProduct(purchaseToken: string): Promise<void> {
        return this.plugin.consumeProduct({purchaseToken: purchaseToken})
    }

    disconnectPayment(): Promise<void> {
        return this.plugin.disconnectPayment()
    }

    getInAppSkuDetails(productIdsJson: string): Promise<SkuDetails> {
        return this.plugin.getInAppSkuDetails({productIdsJson: productIdsJson})
    }

    getPurchasedProducts(): Promise<PurchaseInfoList> {
        return this.plugin.getPurchasedProducts()
    }

    getSubscribedProducts(): Promise<PurchaseInfoList> {
        return this.plugin.getSubscribedProducts()
    }

    getSubscriptionSkuDetails(productIdsJson: string): Promise<SkuDetails> {
        return this.plugin.getSubscriptionSkuDetails({productIdsJson: productIdsJson})
    }

    purchaseProduct(productId: string, payload?: string, dynamicPriceToken?: string): Promise<PurchaseInfo> {
        return this.plugin.purchaseProduct({
            productId: productId,
            payload: payload,
            dynamicPriceToken: dynamicPriceToken
        })
    }

    queryPurchaseProduct(productId: string): Promise<PurchaseInfo> {
        return this.plugin.queryPurchaseProduct({productId: productId})
    }

    querySubscribeProduct(productId: string): Promise<PurchaseInfo> {
        return this.plugin.querySubscribeProduct({productId: productId})
    }

    subscribeProduct(productId: string, payload?: string, dynamicPriceToken?: string): Promise<PurchaseInfo> {
        return this.plugin.subscribeProduct({
            productId: productId,
            payload: payload,
            dynamicPriceToken: dynamicPriceToken
        })
    }

}
