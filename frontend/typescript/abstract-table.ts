// /**
//  * @template R The shape of the data to make each row.
//  */
// export interface Table<R> {
//
//     table: HTMLTableElement;
//     headers: string[];
//     headerKeys: (keyof R)[];
//
//     /**
//      * Get the table element stored in this class.
//      */
//     getTable(): HTMLTableElement;
//
//     /**
//      * Deletes all current rows and sets the rows to the data in data param.
//      * @param data The data to set the table to. Should be of shape {@link R}
//      */
//     setRows(data: R[]): void;
//
//     /**
//      * Appends one more row to the current rows.
//      * @param data Data to append as a row.
//      */
//     appendRow(data: R): void;
//
//     /**
//      * Appends more rows to the current rows.
//      * @param data List of data to append as rows.
//      */
//     appendRows(data: R[]): void;
//
//     /**
//      * Create a row to then be appended/set in the table.
//      * @param data The data to create a row for.
//      */
//     createRow(data: R): HTMLTableRowElement;
// }

export abstract class Table<R extends Object> {

    table: HTMLTableElement;
    tableBody: HTMLTableSectionElement;
    headers: Header<R>[];

    constructor(exampleObject: R, displayNamesAndOrder?: Record<keyof R, string>) {
        this.table = document.createElement("table")
        this.tableBody = document.createElement("tbody");

        this.headers = this.createHeadersFromType(exampleObject, displayNamesAndOrder);
        this.setHeaderRow();

        this.table.appendChild(this.tableBody);

        this.loadCSS()
    }

    /**
     * Creates a list of Header objects to be used in a table for the header cell values and column types.
     * @param exampleObject An example of the object to be displaying. Can be an object with default values.
     * @param displayNamesAndOrder A object with the same properties, but all string type for the header cell values.
     * Used by passing an object with the keys of R and the name preferred. E.g. for a heist:
     * ```heistColumnOrder = { id: "ID", name: "Heist Name" }```.
     *
     */
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

    loadCSS() {
        const link = document.createElement("link");
        link.rel = "stylesheet"
        link.href = "../css/rows.css"; // Relative path from JS folder.
        link.type = 'text/css';
        document.head.appendChild(link);
    }

    getTable(): HTMLTableElement {
        return this.table;
    }

    /**
     * Appends one more row to the current rows.
     * @param data Data to append as a row.
     */
    appendRow(data: R): void {
        const row = this.createPopulatedRow(data)
        this.tableBody.appendChild(row)
    }

    appendRows(data: R[]): void {
        for (const element of data) {
            this.appendRow(element)
        }
    }


    createPopulatedRow(data: R): HTMLTableRowElement {
        const row = document.createElement("tr");

        for (const header of this.headers) {
            const cell: HTMLTableCellElement = document.createElement("td");
            const value = data[header.key]

            let cellValue = "";

            switch (header.type) {
                case "boolean":
                    cellValue = value ? "✔" : "✖";
                    break;

                case "number":
                    cellValue = String(value);
                    break;

                default:
                    cellValue = value != null ? String(value) : "";
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

}

type Header<R> = {
    key: keyof R; // The name of the field.
    value: string; // The name of the header cell
    type: string; // The type of the field, can be string since typesof are returned as strings.
}