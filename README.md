# capacitor-poolakey

A bridge for CafeBazaar (Android App Store) in-app-billing SDK (Poolakey) in CapacitorJs

## Install

```bash
npm install capacitor-poolakey
npx cap sync
```
or
```bash
yarn add capacitor-poolakey
npx cap sync
```

## Usage
```typescript
import {CapacitorPoolakey} from "capacitor-poolakey";

class myClassComponent extends React.Component {
    
    getPurchasedProducts() {
        CapacitorPoolakey.getPurchasedProducts().then((res) => {
            console.log("poolakey: getPurchasedProducts succeed")
            console.log(res?.list)
        }).catch(err => {
            console.log(`poolakey: getPurchasedProducts failed: ${err}`)
        })
    }

    componentDidMount() {
        CapacitorPoolakey.connectPayment(rsaPublicKey).then(() => {
            console.log("poolakey: connected")
            this.getPurchasedProducts()
        }).catch(err => {
            console.log(`poolakey: failed to connect: ${err}`)
        })
    }

    componentWillUnmount() {
        CapacitorPoolakey.disconnectPayment().then(() => {
            console.log("poolakey: disconnected on unmount")
        }).catch(err => {
            console.log(`poolakey: failed to disconnect on unmount: ${err}`)
        })
    }
}
```

## API

<docgen-index>

* [`connectPayment(...)`](#connectpayment)
* [`disconnectPayment()`](#disconnectpayment)
* [`purchaseProduct(...)`](#purchaseproduct)
* [`subscribeProduct(...)`](#subscribeproduct)
* [`consumeProduct(...)`](#consumeproduct)
* [`getPurchasedProducts()`](#getpurchasedproducts)
* [`getSubscribedProducts()`](#getsubscribedproducts)
* [`queryPurchaseProduct(...)`](#querypurchaseproduct)
* [`querySubscribeProduct(...)`](#querysubscribeproduct)
* [`getInAppSkuDetails(...)`](#getinappskudetails)
* [`getSubscriptionSkuDetails(...)`](#getsubscriptionskudetails)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### connectPayment(...)

```typescript
connectPayment(rsaPublicKey: string) => Promise<void>
```

| Param              | Type                |
| ------------------ | ------------------- |
| **`rsaPublicKey`** | <code>string</code> |

--------------------


### disconnectPayment()

```typescript
disconnectPayment() => Promise<void>
```

--------------------


### purchaseProduct(...)

```typescript
purchaseProduct(productId: string, payload?: string | undefined, dynamicPriceToken?: string | undefined) => Promise<PurchaseInfo>
```

| Param                   | Type                |
| ----------------------- | ------------------- |
| **`productId`**         | <code>string</code> |
| **`payload`**           | <code>string</code> |
| **`dynamicPriceToken`** | <code>string</code> |

**Returns:** <code>Promise&lt;<a href="#purchaseinfo">PurchaseInfo</a>&gt;</code>

--------------------


### subscribeProduct(...)

```typescript
subscribeProduct(productId: string, payload?: string | undefined, dynamicPriceToken?: string | undefined) => Promise<PurchaseInfo>
```

| Param                   | Type                |
| ----------------------- | ------------------- |
| **`productId`**         | <code>string</code> |
| **`payload`**           | <code>string</code> |
| **`dynamicPriceToken`** | <code>string</code> |

**Returns:** <code>Promise&lt;<a href="#purchaseinfo">PurchaseInfo</a>&gt;</code>

--------------------


### consumeProduct(...)

```typescript
consumeProduct(purchaseToken: string) => Promise<void>
```

| Param               | Type                |
| ------------------- | ------------------- |
| **`purchaseToken`** | <code>string</code> |

--------------------


### getPurchasedProducts()

```typescript
getPurchasedProducts() => Promise<PurchaseInfoList>
```

**Returns:** <code>Promise&lt;<a href="#purchaseinfolist">PurchaseInfoList</a>&gt;</code>

--------------------


### getSubscribedProducts()

```typescript
getSubscribedProducts() => Promise<PurchaseInfoList>
```

**Returns:** <code>Promise&lt;<a href="#purchaseinfolist">PurchaseInfoList</a>&gt;</code>

--------------------


### queryPurchaseProduct(...)

```typescript
queryPurchaseProduct(productId: string) => Promise<PurchaseInfo>
```

| Param           | Type                |
| --------------- | ------------------- |
| **`productId`** | <code>string</code> |

**Returns:** <code>Promise&lt;<a href="#purchaseinfo">PurchaseInfo</a>&gt;</code>

--------------------


### querySubscribeProduct(...)

```typescript
querySubscribeProduct(productId: string) => Promise<PurchaseInfo>
```

| Param           | Type                |
| --------------- | ------------------- |
| **`productId`** | <code>string</code> |

**Returns:** <code>Promise&lt;<a href="#purchaseinfo">PurchaseInfo</a>&gt;</code>

--------------------


### getInAppSkuDetails(...)

```typescript
getInAppSkuDetails(productIdsJson: string) => Promise<SkuDetails>
```

| Param                | Type                |
| -------------------- | ------------------- |
| **`productIdsJson`** | <code>string</code> |

**Returns:** <code>Promise&lt;<a href="#skudetails">SkuDetails</a>&gt;</code>

--------------------


### getSubscriptionSkuDetails(...)

```typescript
getSubscriptionSkuDetails(productIdsJson: string) => Promise<SkuDetails>
```

| Param                | Type                |
| -------------------- | ------------------- |
| **`productIdsJson`** | <code>string</code> |

**Returns:** <code>Promise&lt;<a href="#skudetails">SkuDetails</a>&gt;</code>

--------------------


### Interfaces


#### PurchaseInfo

| Prop                   | Type                |
| ---------------------- | ------------------- |
| **`orderId`**          | <code>string</code> |
| **`purchaseToken`**    | <code>string</code> |
| **`developerPayload`** | <code>string</code> |
| **`packageName`**      | <code>string</code> |
| **`purchaseState`**    | <code>number</code> |
| **`purchaseTime`**     | <code>number</code> |
| **`productId`**        | <code>string</code> |
| **`dataSignature`**    | <code>string</code> |


#### PurchaseInfoList

| Prop       | Type                        |
| ---------- | --------------------------- |
| **`list`** | <code>PurchaseInfo[]</code> |


#### SkuDetails

| Prop              | Type                |
| ----------------- | ------------------- |
| **`sku`**         | <code>string</code> |
| **`title`**       | <code>string</code> |
| **`type`**        | <code>string</code> |
| **`price`**       | <code>string</code> |
| **`description`** | <code>string</code> |

</docgen-api>
