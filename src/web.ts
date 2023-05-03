import { WebPlugin } from '@capacitor/core';

import type {CapacitorPoolakeyPlugin} from './definitions';

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
}
