export interface CapacitorPoolakeyPlugin {
    echo(options: { value: string }): Promise<{ value: string }>;

    connectPayment(options: connectPaymentParams): Promise<void>;

    disconnectPayment(): Promise<void>;

    purchaseProduct(options: purchaseSubscribeProductParams): Promise<void>;

    subscribeProduct(options: purchaseSubscribeProductParams): Promise<void>;
}

export interface connectPaymentParams {
    rsaPublicKey: string,
}

export interface purchaseSubscribeProductParams {
    productId: string,
    payload?: string,
    dynamicPriceToken?: string,
}
