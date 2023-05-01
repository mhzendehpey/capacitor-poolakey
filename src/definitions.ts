export interface CapacitorPoolakeyPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
