export interface CapacitorPoolakeyPlugin {
    connectPayment(rsaPublicKey: string): Promise<void>;

    disconnectPayment(): Promise<void>;

    purchaseProduct(productId: string, payload?: string, dynamicPriceToken?: string): Promise<PurchaseInfo>;

    subscribeProduct(productId: string, payload?: string, dynamicPriceToken?: string): Promise<PurchaseInfo>;

    consumeProduct(purchaseToken: string): Promise<void>;

    getPurchasedProducts(): Promise<PurchaseInfoList>;

    getSubscribedProducts(): Promise<PurchaseInfoList>;

    queryPurchaseProduct(productId: string): Promise<PurchaseInfo>;

    querySubscribeProduct(productId: string): Promise<PurchaseInfo>;

    getInAppSkuDetails(productIdsJson: string): Promise<SkuDetails>;

    getSubscriptionSkuDetails(productIdsJson: string): Promise<SkuDetails>;
}

export interface PurchaseInfo {
    orderId: string,
    purchaseToken: string,
    developerPayload: string,
    packageName: string,
    purchaseState: number
    purchaseTime: number,
    productId: string,
    dataSignature: string,
}

export interface PurchaseInfoList {
    list: PurchaseInfo[],
}

export interface SkuDetails {
    sku: string,
    title: string,
    type: string,
    price: string,
    description: string,
}
