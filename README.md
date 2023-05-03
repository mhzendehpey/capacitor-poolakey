# capacitor-poolakey

A bridge for cafebazaar in-app-billing SDK (Poolakey) in CapacitorJs

## Install

```bash
npm install capacitor-poolakey
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`connectPayment(...)`](#connectpayment)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### connectPayment(...)

```typescript
connectPayment(options: connectPaymentParams) => Promise<void>
```

| Param         | Type                                                                  |
| ------------- | --------------------------------------------------------------------- |
| **`options`** | <code><a href="#connectpaymentparams">connectPaymentParams</a></code> |

--------------------


### Interfaces


#### connectPaymentParams

| Prop               | Type                |
| ------------------ | ------------------- |
| **`rsaPublicKey`** | <code>string</code> |

</docgen-api>
