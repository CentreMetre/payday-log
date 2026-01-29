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

/**
 * Used to provide the header labels to the table. Can also define column order.
 */
type HeaderMapping<T> = Partial<Record<keyof T, string>>;

export abstract class Table<R extends Object> {

    table: HTMLTableElement;
    headers: Header[];

    constructor(exampleObject: R, displayNames?: Record<keyof R, string>) {
        this.table = document.createElement("table")

        this.headers = this.createHeadersFromType(exampleObject, displayNames);
    }

    /**
     * Creates a list of Header objects to be used in a table for the header cell values and column types.
     * @param exampleObject An example of the object to be displaying. Can be an object with default values.
     * @param displayNames A object with the same properties, but all string type for the header cell values.
     */
    createHeadersFromType(exampleObject: R, displayNames?: Record<keyof R, string>): Header[] {
        const headers: Header[] = [];

        for (const key in exampleObject) {
            if (Object.prototype.hasOwnProperty.call(exampleObject, key)) {
                headers.push({
                    key,
                    value: displayNames?.[key] || key,
                    type: typeof exampleObject[key],
                })
            }
        }
        return headers;
    }

    appendRow(data: R): void {
        const row = document.createElement("tr");

        this.table.append(row);
    }

    appendRows(data: R[]): void {
        for (const element of data) {
            this.appendRow(element)
        }
    }

    createPopulatedRow(data: R): HTMLTableRowElement {
        return new HTMLTableRowElement();
    }

    getTable(): HTMLTableElement {
        return this.table;
    }

    setRows(data: R[]): void {
    }

}

// type head

// type headerValues<R extends Record<keyof R, string>

type Header = {
    key: string; // The name of the field.
    value: string; // The name of the header cell
    type: any; // The type of the field
}