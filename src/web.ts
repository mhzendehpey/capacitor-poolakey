import {WebPlugin} from '@capacitor/core';

import type {CapacitorPoolakeyPlugin} from './definitions';

export class CapacitorPoolakeyWeb extends WebPlugin implements CapacitorPoolakeyPlugin {

    message = "Not available in web."

    async connectPayment(): Promise<void> {
        throw new Error(this.message)
    }

    async disconnectPayment(): Promise<void> {
        throw new Error(this.message)
    }

    async purchaseProduct(): Promise<any> {
        throw new Error(this.message)
    }

    async subscribeProduct(): Promise<any> {
        throw new Error(this.message)
    }

    async consumeProduct(): Promise<void> {
        throw new Error(this.message)
    }

    async getPurchasedProducts(): Promise<any> {
        throw new Error(this.message)
    }

    async getSubscribedProducts(): Promise<any> {
        throw new Error(this.message)
    }

    async queryPurchaseProduct(): Promise<any> {
        throw new Error(this.message)
    }

    async querySubscribeProduct(): Promise<any> {
        throw new Error(this.message)
    }

    async getInAppSkuDetails(): Promise<any> {
        throw new Error(this.message)
    }

    async getSubscriptionSkuDetails(): Promise<any> {
        throw new Error(this.message)
    }

}
