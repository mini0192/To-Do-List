import axios from "axios";
import {ToDoSubSaveType, ToDoSubFindByIdType, ToDoSubPatchType} from "../type/ToDoSub.tsx";

const API_URL = import.meta.env.VITE_API_URL;

export const ToDoSubSaveApi = async (parentId: number, data: ToDoSubSaveType): Promise<void> => {
    const res = await axios.post(`${API_URL}/api/v1/todos/subs/${parentId}`, data);
    return res.data;
}

export const ToDoSubPatchApi = async (id: number, data: ToDoSubPatchType): Promise<void>  => {
    const res = await axios.patch(`${API_URL}/api/v1/todos/subs/status/${id}`, data)
    return res.data;
}

export const ToDoSubFindByIdApi = async (id: number): Promise<ToDoSubFindByIdType> => {
    const res = await axios.get(`${API_URL}/api/v1/todos/subs/${id}`);
    return res.data;
}

export const ToDoSubPutApi = async (id: number, data: ToDoSubSaveType): Promise<void> => {
    const res = await axios.put(`${API_URL}/api/v1/todos/subs/${id}`, data);
    return res.data;
}

export const ToDoSubDeleteApi = async (id: number): Promise<void>  => {
    const res = await axios.delete(`${API_URL}/api/v1/todos/subs/${id}`)
    return res.data;
}