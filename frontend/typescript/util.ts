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

export function createToolTip(toolTipText: string, icon: string = "🛈"): HTMLSpanElement {
    const spanEl = document.createElement('span');
    spanEl.textContent = icon;
    spanEl.title = toolTipText;
    spanEl.style.cursor = "help";

    return spanEl;
}

/**
 * Creates a new date time for use in a datetime input with 3 milliseconds.
 */
export function createNewDateStringForForm(): string {
    const date = new Date();

    const pad = (n: number, z = 2) => n.toString().padStart(z, "0");
    return (
        date.getFullYear() +
        "-" +
        pad(date.getMonth() + 1) +
        "-" +
        pad(date.getDate()) +
        "T" +
        pad(date.getHours()) +
        ":" +
        pad(date.getMinutes()) +
        ":" +
        pad(date.getSeconds()) +
        "." +
        pad(date.getMilliseconds(), 3)
    );
}