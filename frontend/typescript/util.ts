const ISO_MS_REGEX =
    /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}\.\d+(Z|[+-]\d{2}:\d{2})?$/;

export function isIsoDateWithMs(value: string): boolean {
    if (!ISO_MS_REGEX.test(value)) return false;
    return !Number.isNaN(Date.parse(value));
}

export function padIsoMilliseconds(value: string): string {
    const date = new Date(value);
    if (isNaN(date.getTime())) return value; // invalid date, return as-is

    const ms = date.getMilliseconds().toString().padStart(3, '0'); // always 3 digits

    // split original value into date and time parts
    const [datePart, timePart] = value.split('T');
    if (!timePart) return value; // not a datetime string

    // split time into seconds and fractional part
    const [timeSec] = timePart.split('.');

    return `${datePart}T${timeSec}.${ms}`; // keep the same format, no Z added
}