export interface CapacitorPoolakeyPlugin {
    connectPayment(options: ConnectPaymentParams): Promise<void>;

    disconnectPayment(): Promise<void>;

    purchaseProduct(options: PurchaseSubscribeProductParams): Promise<PurchaseInfo>;

    subscribeProduct(options: PurchaseSubscribeProductParams): Promise<PurchaseInfo>;

    consumeProduct(options: ConsumeProductParams): Promise<void>;

    getPurchasedProducts(): Promise<PurchasedProductsResult>;

    getSubscribedProducts(): Promise<PurchasedProductsResult>;

    queryPurchaseProduct(options: QueryProductParams): Promise<PurchaseInfo>;

    querySubscribeProduct(options: QueryProductParams): Promise<PurchaseInfo>;

    getInAppSkuDetails(options: GetSkuDetailsParams): Promise<SkuDetails>;

    getSubscriptionSkuDetails(options: GetSkuDetailsParams): Promise<SkuDetails>;
}

export interface ConnectPaymentParams {
    rsaPublicKey: string,
}

export interface PurchaseSubscribeProductParams {
    productId: string,
    payload?: string,
    dynamicPriceToken?: string,
}

export interface ConsumeProductParams {
    purchaseToken: string,
}

export interface QueryProductParams {
    productId: string,
}

export interface GetSkuDetailsParams {
    productIdsJson: string,
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

export interface PurchasedProductsResult {
    list: PurchaseInfo[],
}

export interface SkuDetails {
    sku: string,
    title: string,
    type: string,
    price: string,
    description: string,
}
