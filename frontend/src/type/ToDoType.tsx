import {Priority, Status} from "./EnumType.tsx";
import {ToDoSubFindByIdType} from "./ToDoSub.tsx";

export type ToDoFindAllType = {
    "content": Array<{
            "id": number,
            "title": string,
            "priority": string,
            "status": Status,
            "unixTime": number
    }>,
    "page": {
    "size": number,
        "number": number,
        "totalElements": number,
        "totalPages": number
    }
}

export type ToDoSaveType = {
    "title": string,
    "content": string,
    "priority": Priority,
    "unixTime": number
}

export type ToDoFindByIdType = {
    "id": number,
    "title": string,
    "content": string,
    "priority": Priority,
    "status": Status,
    "unixTime": number,
    "sub": Array<ToDoSubFindByIdType>
}

export type ToDoPutType = {
    "title": string,
    "content": string,
    "priority": Priority,
    "unixTime": number | string
}

export type ToDoPatchType = {
    "status": Status
}