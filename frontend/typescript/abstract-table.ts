import {isIsoDateWithMs, padIsoMilliseconds} from "./util.js";

/**
 * @template R A type that the shape of a row should be. E.g.
 * ```
 * type Heist = {
 *     id: number;
 *     name: string;
 * }
 * ```
 * Then `Heist` would be passed as the generic.
 */
export abstract class Table<R extends Object> {

    table: HTMLTableElement;
    tableBody: HTMLTableSectionElement;
    headers: Header<R>[];

    messageEl: HTMLElement | null;

    /**
     * Creates a list of Header objects to be used in a table for the header cell values and column types.
     * @param exampleObject An instance of the type to be displaying. Can be an object with default values such as
     * `0`, `""`, `false` or `true`. E.g. for a heist ```{ id: 0; name: ""; }```
     * @param displayNamesAndOrder A object with the same properties, but all string type for the header cell values.
     * Used by passing an object with the keys of R and the name preferred. E.g. for a heist:
     * ```{ id: "ID", name: "Heist Name" }```, this makes the columns appear in the order of ID then Heist Name.
     * @param messageElement Used to display messaged related to data output. Optional.
     * Omit (undefined) to use the class provided HTMLParagraphElement.
     * Pass an HTMLElement tp ise a custom element.
     * Pass null to disable automatic message output.
     */
    constructor(exampleObject: R, displayNamesAndOrder?: Record<keyof R, string>, messageElement?: HTMLElement | null | undefined) {

        this.table = document.createElement("table")
        this.tableBody = document.createElement("tbody");

        this.headers = this.createHeadersFromType(exampleObject, displayNamesAndOrder);
        this.setHeaderRow();

        this.table.appendChild(this.tableBody);

        this.loadCSS()

        this.messageEl = messageElement === undefined ? document.createElement("p") : messageElement;
    }

    createHeadersFromType(
        exampleObject: R, // Needed for runtime, since types don't exist in JS.
        displayNamesAndOrder?: Record<keyof R, string>
    ): Header<R>[] {
        const keys = displayNamesAndOrder
            ? (Object.keys(displayNamesAndOrder) as (keyof R)[])
            : (Object.keys(exampleObject) as (keyof R)[]);

        return keys.map(key => ({
            key,
            value: displayNamesAndOrder?.[key] ?? String(key),
            type: typeof exampleObject[key],
        }));
    }

    setHeaderRow() {
        const thead = document.createElement("thead");
        const headerRow = document.createElement("tr");

        for (const header of this.headers) {
            const th = document.createElement("th")
            th.textContent = header.value;
            headerRow.appendChild(th);
        }

        thead.appendChild(headerRow);
        this.table.appendChild(thead);
    }

    /**
     * Loads the CSS for tables into the html file for proper styling.
     */
    loadCSS() {
        const link = document.createElement("link");
        link.rel = "stylesheet"
        link.href = "../css/table.css"; // Relative path from JS folder.
        link.type = 'text/css';
        document.head.appendChild(link);
    }

    /**
     * Returns the table element of this instance for putting onto a page.
     */
    getTable(): HTMLTableElement {
        return this.table;
    }

    /**
     * Appends one more row to the current rows.
     * @param data Data to append as a row.
     */
    appendRow(data: R): void {
        debugger;
        const row = this.createPopulatedRow(data)
        this.tableBody.appendChild(row)
    }

    appendRows(data: R[]): void {
        for (const element of data) {
            this.appendRow(element)
        }
    }

    /**
     * Creates a row, populated with data, for displaying in the table.
     * @param data The data in the shape of `R`, with values populated.
     * @return A HTMLTableRowElement that can then be set on a table.
     */
    createPopulatedRow(data: R): HTMLTableRowElement {
        const row = document.createElement("tr");

        for (const header of this.headers) {
            const cell: HTMLTableCellElement = document.createElement("td");
            const value = data[header.key]

            let cellValue = "";

            switch (header.type) {
                case "boolean":
                    cell.classList.add("table-cell-boolean");
                    cellValue = value ? "✔" : "✖";
                    cell.classList.add(value ? "table-cell-boolean-true" : "table-cell-boolean-false");
                    break;

                case "number":
                    cellValue = String(value);
                    cell.classList.add("table-cell-number");
                    break;

                default:
                    const strValue = String(value)
                    if (isIsoDateWithMs(strValue))
                    {
                        const strValueDateTime = padIsoMilliseconds(strValue);
                        cell.classList.add("table-cell-datetime")
                        const date = strValueDateTime.split("T")[0]
                        const time = strValueDateTime.split("T")[1]
                        cellValue = `${date} ${time}`;
                    }
                    else {
                        cellValue = value != null ? String(value) : "";
                    }
            }
            cell.textContent = cellValue;
            row.append(cell);
        }

        return row;
    }

    /**
     * Deletes all current rows.
     */
    clearRows(): void {
        while (this.tableBody.firstChild) {
            this.tableBody.removeChild(this.tableBody.firstChild)
        }
    }

    /**
     * Deletes all current rows and sets a single row to the data in data param.
     * @param data The data to set the table to. Should be of shape {@link R}
     */
    setSingleRow(data: R): void {
        this.clearRows()
        this.appendRow(data)
    }

    /**
     * Deletes all current rows and sets the rows to the data in data param.
     * @param data The data to set the table to. Should be of shape {@link R}
     */
    setRows(data: R[]): void {
        this.clearRows()
        this.appendRows(data)
    }

    clearMessage() {
        if (!this.messageEl) return;
        this.messageEl.textContent = ""
    }

    appendMessage(message: string) {
        if (!this.messageEl) return;
        this.messageEl.textContent = `${this.messageEl.textContent} ${message}`
    }

    setMessage(message: string) {
        if (!this.messageEl) return;
        this.clearMessage();
        this.appendMessage(message);
    }
}

type Header<R> = {
    key: keyof R; // The name of the field.
    value: string; // The name of the header cell
    type: string; // The type of the field, can be string since typesof are returned as strings.
}