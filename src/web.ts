import {WebPlugin} from '@capacitor/core';

import type {CapacitorPoolakeyPlugin, SkuDetails, PurchaseInfo} from './definitions';

export class CapacitorPoolakeyWeb extends WebPlugin implements CapacitorPoolakeyPlugin {
    async echo(options: { value: string }): Promise<{ value: string }> {
        console.log('ECHO', options);
        return options;
    }

    async connectPayment(): Promise<void> {
        console.log("Not available in web.")
    }

    async disconnectPayment(): Promise<void> {
        console.log("Not available in web.")
    }

    async purchaseProduct(): Promise<void> {
        console.log("Not available in web.")
    }

    async subscribeProduct(): Promise<void> {
        console.log("Not available in web.")
    }

    async consumeProduct(): Promise<void> {
        console.log("Not available in web.")
    }

    async getPurchasedProducts(): Promise<PurchaseInfo[] | void> {
        console.log("Not available in web.")
    }

    async getSubscribedProducts(): Promise<PurchaseInfo[] | void> {
        console.log("Not available in web.")
    }

    async queryPurchaseProduct(): Promise<PurchaseInfo | void> {
        console.log("Not available in web.")
    }

    async querySubscribeProduct(): Promise<PurchaseInfo | void> {
        console.log("Not available in web.")
    }

    async getInAppSkuDetails(): Promise<SkuDetails | void> {
        console.log("Not available in web.")
    }

    async getSubscriptionSkuDetails(): Promise<SkuDetails | void> {
        console.log("Not available in web.")
    }

}
