import { registerPlugin } from '@capacitor/core';

import type { CapacitorPoolakeyPlugin } from './definitions';

const CapacitorPoolakey = registerPlugin<CapacitorPoolakeyPlugin>('CapacitorPoolakey', {
  web: () => import('./web').then(m => new m.CapacitorPoolakeyWeb()),
});

export * from './definitions';
export { CapacitorPoolakey };
