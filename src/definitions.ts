export interface CapacitorPoolakeyPlugin {
    echo(options: { value: string }): Promise<{ value: string }>;

    connectPayment(options: ConnectPaymentParams): Promise<void>;

    disconnectPayment(): Promise<void>;

    purchaseProduct(options: PurchaseSubscribeProductParams): Promise<void>;

    subscribeProduct(options: PurchaseSubscribeProductParams): Promise<void>;

    consumeProduct(options: ConsumeProductParams): Promise<void>;
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
