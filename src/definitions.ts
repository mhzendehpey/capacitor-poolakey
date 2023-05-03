export interface CapacitorPoolakeyPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  connectPayment(options: connectPaymentParams): Promise<void>;
}

export interface connectPaymentParams {
  rsaPublicKey: string,
}
