export interface CapacitorPoolakeyPlugin {
    connectPayment(options: ConnectPaymentParams): Promise<void>;

    disconnectPayment(): Promise<void>;

    purchaseProduct(options: PurchaseSubscribeProductParams): Promise<PurchaseInfo | void>;

    subscribeProduct(options: PurchaseSubscribeProductParams): Promise<PurchaseInfo | void>;

    consumeProduct(options: ConsumeProductParams): Promise<void>;

    getPurchasedProducts(): Promise<PurchaseInfo[] | void>;

    getSubscribedProducts(): Promise<PurchaseInfo[] | void>;

    queryPurchaseProduct(options: QueryProductParams): Promise<PurchaseInfo | void>;

    querySubscribeProduct(options: QueryProductParams): Promise<PurchaseInfo | void>;

    getInAppSkuDetails(options: GetSkuDetailsParams): Promise<SkuDetails | void>;

    getSubscriptionSkuDetails(options: GetSkuDetailsParams): Promise<SkuDetails | void>;
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
    payload: string,
    packageName: string,
    purchaseState: number
    purchaseTime: number,
    productId: string,
    dataSignature: string,
}

export interface SkuDetails {
    sku: string,
    title: string,
    type: string,
    price: string,
    description: string,
}
