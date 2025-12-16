export interface PoolakeyBridge {
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

export interface CapacitorPoolakeyPlugin {
  connectPayment(options: ConnectPaymentParams): Promise<void>;

  disconnectPayment(): Promise<void>;

  purchaseProduct(options: PurchaseSubscribeProductParams): Promise<PurchaseInfo>;

  subscribeProduct(options: PurchaseSubscribeProductParams): Promise<PurchaseInfo>;

  consumeProduct(options: ConsumeProductParams): Promise<void>;

  consumeProduct(purchaseToken: string): Promise<void>;

  getPurchasedProducts(): Promise<PurchaseInfoList>;

  getSubscribedProducts(): Promise<PurchaseInfoList>;

  queryPurchaseProduct(options: QueryProductParams): Promise<PurchaseInfo>;

  querySubscribeProduct(options: QueryProductParams): Promise<PurchaseInfo>;

  getInAppSkuDetails(options: GetSkuDetailsParams): Promise<SkuDetails>;

  getSubscriptionSkuDetails(options: GetSkuDetailsParams): Promise<SkuDetails>;
}

export interface ConnectPaymentParams {
  rsaPublicKey: string;
}

export interface PurchaseSubscribeProductParams {
  productId: string;
  payload?: string;
  dynamicPriceToken?: string;
}

export interface ConsumeProductParams {
  purchaseToken: string;
}

export interface QueryProductParams {
  productId: string;
}

export interface GetSkuDetailsParams {
  productIdsJson: string;
}

export interface PurchaseInfo {
  orderId: string;
  purchaseToken: string;
  developerPayload: string;
  packageName: string;
  purchaseState: number;
  purchaseTime: number;
  productId: string;
  dataSignature: string;
}

export interface PurchaseInfoList {
  list: PurchaseInfo[];
}

export interface SkuDetails {
  sku: string;
  title: string;
  type: string;
  price: string;
  description: string;
}
