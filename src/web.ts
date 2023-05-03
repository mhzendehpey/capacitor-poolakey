import {WebPlugin} from '@capacitor/core';

import type {CapacitorPoolakeyPlugin} from './definitions';

export class CapacitorPoolakeyWeb extends WebPlugin implements CapacitorPoolakeyPlugin {

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

    async getPurchasedProducts(): Promise<void> {
        console.log("Not available in web.")
    }

    async getSubscribedProducts(): Promise<void> {
        console.log("Not available in web.")
    }

    async queryPurchaseProduct(): Promise<void> {
        console.log("Not available in web.")
    }

    async querySubscribeProduct(): Promise<void> {
        console.log("Not available in web.")
    }

    async getInAppSkuDetails(): Promise<void> {
        console.log("Not available in web.")
    }

    async getSubscriptionSkuDetails(): Promise<void> {
        console.log("Not available in web.")
    }

}
