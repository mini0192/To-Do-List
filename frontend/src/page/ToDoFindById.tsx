import {useNavigate, useParams} from "react-router-dom";
import { useForm } from "react-hook-form";
import { ToDoPatchType, ToDoPutType } from "../type/ToDoType.tsx";
import { useEffect, useState } from "react";
import { ToDoDeleteApi, ToDoFindByIdApi, ToDoPatchApi, ToDoPutApi } from "../api/ToDoApis.tsx";
import { FromUnixTime, ToUnixTime } from "../config/config.tsx";
import { Status } from "../type/EnumType.tsx";
import {ToDoSubFindByIdType, ToDoSubPatchType} from "../type/ToDoSub.tsx";
import {ToDoSubPatchApi} from "../api/ToDoSubApis.tsx";
import {ToDoSubFindById} from "./sub/ToDoSubFindById.tsx";

export const ToDoFindById = () => {
    const { id } = useParams();

    const navigate = useNavigate();
    const [status, setStatus] = useState<Status | "">("");
    const [toDoSubs, setToDoSubs] = useState<ToDoSubFindByIdType[]>([]);

    const [model, setModel] = useState(0);

    const { register, handleSubmit, setValue } = useForm<ToDoPutType>();

    useEffect(() => {
        ToDoFindByIdApi(Number(id))
            .then(res => {
                setValue("title", res.title);
                setValue("content", res.content);
                setValue("priority", res.priority);
                setValue("unixTime", FromUnixTime(Number(res.unixTime)));
                setStatus(res.status);
                setToDoSubs(res.sub);
            })
            .catch(err => console.log(err));
    }, [id, setValue, model]);

    const handleStatusChange = (id: number, status: Status) => {
        const data: ToDoPatchType = { status };
        ToDoPatchApi(id, data)
            .then(() => setStatus(status))
            .catch(err => console.log(err));
    };

    const handleSubStatusChange = (id: number, status: Status) => {
        const data: ToDoSubPatchType = { status };

        ToDoSubPatchApi(id, data)
            .then(() => {
                setToDoSubs(prevSubs =>
                    prevSubs.map(sub =>
                        sub.id === id ? { ...sub, status } : sub
                    )
                );
            })
            .catch(err => console.log(err));
    };

    const onSubmit = (data: ToDoPutType) => {
        data.unixTime = ToUnixTime(data.unixTime);
        ToDoPutApi(Number(id), data)
            .then(() => navigate("/"))
            .catch(err => console.log(err));
    };

    useEffect(() => {
        console.log(model)
    }, [model]);

    return (
        <>
            {model !== 0 && <div className="modal-overlay"></div>}
            {model !== 0 ? <ToDoSubFindById id={model} setModel={setModel} /> : null}
            <div className="d-flex justify-content-center align-items-center vh-100">
                <div className="d-flex gap-3">
                    <div className="card p-3 shadow-sm" style={{ width: "400px", borderRadius: "10px" }}>
                        <h4 className="text-center mb-3 fw-semibold">수정하기</h4>
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <div className="mb-2">
                                <label className="fw-bold small">우선순위</label>
                                <select className="form-select form-select-sm" {...register("priority")}>
                                    <option value="HIGH">HIGH</option>
                                    <option value="MEDIUM">MEDIUM</option>
                                    <option value="LOW">LOW</option>
                                    <option value="NONE">NONE</option>
                                </select>
                            </div>

                            <div className="mb-2">
                                <label className="fw-bold small">상태</label>
                                <select className="form-select form-select-sm" value={status}
                                        onChange={e => handleStatusChange(Number(id), e.target.value as Status)}>
                                    <option value="NOT_STARTED">NOT_STARTED</option>
                                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                                    <option value="COMPLETED">COMPLETED</option>
                                </select>
                            </div>

                            <div className="form-floating mb-2">
                                <input className="form-control form-control-sm"
                                       placeholder="Title" {...register("title", {required: "필수 값 입니다"})} />
                                <label className="small fw-bold">제목</label>
                            </div>

                            <div className="form-floating mb-2">
                                <textarea className="form-control form-control-sm" placeholder="Content"
                                          style={{height: "150px"}} {...register("content", {required: "필수 값 입니다"})} />
                                <label className="small fw-bold">내용</label>
                            </div>

                            <div className="mb-2">
                                <label className="fw-bold small">마감 날짜</label>
                                <input type="date"
                                       className="form-control form-control-sm" {...register("unixTime", {required: "날짜와 시간을 입력하세요"})} />
                            </div>

                            <button type="submit" className="btn btn-info btn-sm w-100 py-1 fw-bold text-white">수정
                            </button>
                            <button type="button" className="btn btn-danger btn-sm w-100 py-1 fw-bold text-white mt-2"
                                    onClick={() => {
                                        ToDoDeleteApi(Number(id))
                                            .then(() => navigate("/"))
                                            .catch(err => console.log(err));
                                    }}>삭제
                            </button>
                            <button type="button"
                                    className="btn btn-secondary btn-sm w-100 py-1 fw-bold text-white mt-2"
                                    onClick={() => navigate("/")}>
                                이전
                            </button>
                        </form>
                    </div>

                    <div className="card shadow-sm p-3" style={{width: "500px"}}>
                        <h4 className="text-center fw-bold mb-3">To-Do List</h4>
                        <table className="table table-hover align-middle">
                            <thead>
                            <tr className="table-light text-center">
                                <th scope="col">Title</th>
                                <th scope="col">Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            {toDoSubs?.map((toDoSub: ToDoSubFindByIdType) => (
                                <tr key={toDoSub.id} className="text-center">
                                    <td className="text-start">
                                        <button
                                            type="button"
                                            className="btn btn-link fw-semibold text-decoration-none p-0"
                                            onClick={() => setModel(toDoSub.id)}
                                        >
                                            {toDoSub.title}
                                        </button>

                                    </td>
                                    <td>
                                        <select
                                            className="form-select form-select-sm"
                                            value={toDoSub.status}
                                            onChange={e => handleSubStatusChange(toDoSub.id, e.target.value as Status)}
                                        >
                                            <option value="NOT_STARTED">NOT_STARTED</option>
                                            <option value="IN_PROGRESS">IN_PROGRESS</option>
                                            <option value="COMPLETED">COMPLETED</option>
                                        </select>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                        <div className="d-flex justify-content-center mt-3">
                            <button
                                type="button"
                                className="btn btn-info btn-sm w-100 py-1 fw-bold text-white mt-2"
                                onClick={() => navigate(`/subs/save/${id}`)}
                            >
                                추가
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};
