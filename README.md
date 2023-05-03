# capacitor-poolakey

A bridge for cafebazaar in-app-billing SDK (Poolakey) in CapacitorJs

## Install

```bash
npm install capacitor-poolakey
npx cap sync
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
connectPayment(options: ConnectPaymentParams) => Promise<void>
```

| Param         | Type                                                                  |
| ------------- | --------------------------------------------------------------------- |
| **`options`** | <code><a href="#connectpaymentparams">ConnectPaymentParams</a></code> |

--------------------


### disconnectPayment()

```typescript
disconnectPayment() => Promise<void>
```

--------------------


### purchaseProduct(...)

```typescript
purchaseProduct(options: PurchaseSubscribeProductParams) => Promise<PurchaseInfo>
```

| Param         | Type                                                                                      |
| ------------- | ----------------------------------------------------------------------------------------- |
| **`options`** | <code><a href="#purchasesubscribeproductparams">PurchaseSubscribeProductParams</a></code> |

**Returns:** <code>Promise&lt;<a href="#purchaseinfo">PurchaseInfo</a>&gt;</code>

--------------------


### subscribeProduct(...)

```typescript
subscribeProduct(options: PurchaseSubscribeProductParams) => Promise<PurchaseInfo>
```

| Param         | Type                                                                                      |
| ------------- | ----------------------------------------------------------------------------------------- |
| **`options`** | <code><a href="#purchasesubscribeproductparams">PurchaseSubscribeProductParams</a></code> |

**Returns:** <code>Promise&lt;<a href="#purchaseinfo">PurchaseInfo</a>&gt;</code>

--------------------


### consumeProduct(...)

```typescript
consumeProduct(options: ConsumeProductParams) => Promise<void>
```

| Param         | Type                                                                  |
| ------------- | --------------------------------------------------------------------- |
| **`options`** | <code><a href="#consumeproductparams">ConsumeProductParams</a></code> |

--------------------


### getPurchasedProducts()

```typescript
getPurchasedProducts() => Promise<PurchasesProductsResult>
```

**Returns:** <code>Promise&lt;<a href="#purchasesproductsresult">PurchasesProductsResult</a>&gt;</code>

--------------------


### getSubscribedProducts()

```typescript
getSubscribedProducts() => Promise<PurchasesProductsResult>
```

**Returns:** <code>Promise&lt;<a href="#purchasesproductsresult">PurchasesProductsResult</a>&gt;</code>

--------------------


### queryPurchaseProduct(...)

```typescript
queryPurchaseProduct(options: QueryProductParams) => Promise<PurchaseInfo>
```

| Param         | Type                                                              |
| ------------- | ----------------------------------------------------------------- |
| **`options`** | <code><a href="#queryproductparams">QueryProductParams</a></code> |

**Returns:** <code>Promise&lt;<a href="#purchaseinfo">PurchaseInfo</a>&gt;</code>

--------------------


### querySubscribeProduct(...)

```typescript
querySubscribeProduct(options: QueryProductParams) => Promise<PurchaseInfo>
```

| Param         | Type                                                              |
| ------------- | ----------------------------------------------------------------- |
| **`options`** | <code><a href="#queryproductparams">QueryProductParams</a></code> |

**Returns:** <code>Promise&lt;<a href="#purchaseinfo">PurchaseInfo</a>&gt;</code>

--------------------


### getInAppSkuDetails(...)

```typescript
getInAppSkuDetails(options: GetSkuDetailsParams) => Promise<SkuDetails>
```

| Param         | Type                                                                |
| ------------- | ------------------------------------------------------------------- |
| **`options`** | <code><a href="#getskudetailsparams">GetSkuDetailsParams</a></code> |

**Returns:** <code>Promise&lt;<a href="#skudetails">SkuDetails</a>&gt;</code>

--------------------


### getSubscriptionSkuDetails(...)

```typescript
getSubscriptionSkuDetails(options: GetSkuDetailsParams) => Promise<SkuDetails>
```

| Param         | Type                                                                |
| ------------- | ------------------------------------------------------------------- |
| **`options`** | <code><a href="#getskudetailsparams">GetSkuDetailsParams</a></code> |

**Returns:** <code>Promise&lt;<a href="#skudetails">SkuDetails</a>&gt;</code>

--------------------


### Interfaces


#### ConnectPaymentParams

| Prop               | Type                |
| ------------------ | ------------------- |
| **`rsaPublicKey`** | <code>string</code> |


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


#### PurchaseSubscribeProductParams

| Prop                    | Type                |
| ----------------------- | ------------------- |
| **`productId`**         | <code>string</code> |
| **`payload`**           | <code>string</code> |
| **`dynamicPriceToken`** | <code>string</code> |


#### ConsumeProductParams

| Prop                | Type                |
| ------------------- | ------------------- |
| **`purchaseToken`** | <code>string</code> |


#### PurchasesProductsResult

| Prop       | Type                        |
| ---------- | --------------------------- |
| **`list`** | <code>PurchaseInfo[]</code> |


#### QueryProductParams

| Prop            | Type                |
| --------------- | ------------------- |
| **`productId`** | <code>string</code> |


#### SkuDetails

| Prop              | Type                |
| ----------------- | ------------------- |
| **`sku`**         | <code>string</code> |
| **`title`**       | <code>string</code> |
| **`type`**        | <code>string</code> |
| **`price`**       | <code>string</code> |
| **`description`** | <code>string</code> |


#### GetSkuDetailsParams

| Prop                 | Type                |
| -------------------- | ------------------- |
| **`productIdsJson`** | <code>string</code> |

</docgen-api>
