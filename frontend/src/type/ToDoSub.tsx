import {Status} from "./EnumType.tsx";

export type ToDoSubFindByIdType = {
    "id": number,
    "title": string,
    "content": string,
    "status": Status
}

export type ToDoSubSaveType = {
    "title": string,
    "content": string
}

export type ToDoSubPutType = {
    "title": string,
    "content": string
}

export type ToDoSubPatchType = {
    "status": Status
}