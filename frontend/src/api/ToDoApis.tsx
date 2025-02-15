import axios from "axios";
import {ToDoFindAllType, ToDoFindByIdType, ToDoPatchType, ToDoPutType, ToDoSaveType} from "../type/ToDoType.tsx";
import {Sort} from "../type/EnumType.tsx";

const API_URL = import.meta.env.VITE_API_URL;

export const ToDoFindAllApi = async (sort: Sort): Promise<ToDoFindAllType> => {
    const res = await axios.get(`${API_URL}/api/v1/todos?sort=${sort}`);
    return res.data;
}

export const ToDoSaveApi = async (data: ToDoSaveType): Promise<void> => {
    const res = await axios.post(`${API_URL}/api/v1/todos`, data);
    return res.data;
}

export const ToDoFindByIdApi = async (id: number): Promise<ToDoFindByIdType> => {
    const res = await axios.get(`${API_URL}/api/v1/todos/${id}`);
    return res.data;
}

export const ToDoPutApi = async (id: number, data: ToDoPutType): Promise<void> => {
    const res = await axios.put(`${API_URL}/api/v1/todos/${id}`, data);
    return res.data;
}

export const ToDoPatchApi = async (id: number, data: ToDoPatchType): Promise<void>  => {
    const res = await axios.patch(`${API_URL}/api/v1/todos/status/${id}`, data)
    return res.data;
}

export const ToDoDeleteApi = async (id: number): Promise<void>  => {
    const res = await axios.delete(`${API_URL}/api/v1/todos/${id}`)
    return res.data;
}