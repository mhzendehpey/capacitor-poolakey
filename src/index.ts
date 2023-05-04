import {registerPlugin} from '@capacitor/core';

import {PoolakeyAPI} from "./api";
import type {CapacitorPoolakeyPlugin, PoolakeyBridge} from './definitions';

const CapacitorPoolakey = registerPlugin<CapacitorPoolakeyPlugin>('CapacitorPoolakey', {
    web: () => import('./web').then(m => new m.CapacitorPoolakeyWeb()),
});
const Poolakey: PoolakeyBridge = new PoolakeyAPI(CapacitorPoolakey)

export * from './definitions';
export {Poolakey};
