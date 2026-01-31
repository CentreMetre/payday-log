const ISO_MS_REGEX =
    /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d{3}(Z|[+-]\d{2}:\d{2})?$/;

export function isIsoDateWithMs(value: string): boolean {
    if (!ISO_MS_REGEX.test(value)) return false;
    return !Number.isNaN(Date.parse(value));
}
