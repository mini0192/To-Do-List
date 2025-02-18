import { useEffect, useState } from "react";
import { ToDoFindAllType, ToDoPatchType } from "../type/ToDoType.tsx";
import { ToDoFindAllApi, ToDoPatchApi } from "../api/ToDoApis.tsx";
import { Sort, Status } from "../type/EnumType.tsx";
import { FromUnixTime } from "../config/config.tsx";
import { Link, useNavigate } from "react-router-dom";
import { rtnPrimaryBadge } from "../config/rtnPrimaryBadge.tsx";

export const ToDoFindAll = () => {
    const [toDos, setToDos] = useState<ToDoFindAllType>();
    const navigate = useNavigate();

    useEffect(() => {
        ToDoFindAllApi(Sort.ASC)
            .then(res => setToDos(res))
            .catch(err => console.log(err));
    }, []);

    const handleStatusChange = (id: number, status: Status) => {
        const data: ToDoPatchType = { status };
        ToDoPatchApi(id, data).catch(err => console.log(err));

        setToDos(prevToDos => {
            if (!prevToDos) return prevToDos;
            return {
                ...prevToDos,
                content: prevToDos.content.map(todo =>
                    todo.id === id ? { ...todo, status } : todo
                )
            };
        });
    };

    return (
        <div className="container mt-4">
            <div className="card shadow-sm p-3">
                <h4 className="text-center fw-bold mb-3">일정</h4>
                <table className="table table-hover align-middle">
                    <thead>
                    <tr className="table-light text-center">
                        <th scope="col">제목</th>
                        <th scope="col">중요도</th>
                        <th scope="col">상태</th>
                        <th scope="col">마감 시간</th>
                    </tr>
                    </thead>
                    <tbody>
                    {toDos?.content.map((todo) => (
                        <tr key={todo.id} className="text-center">
                            <td className="text-center">  {/* 중앙 정렬로 변경 */}
                                <Link to={`/${todo.id}`} className="fw-semibold text-decoration-none">
                                    {todo.title}
                                </Link>
                            </td>
                            <td>{rtnPrimaryBadge(todo.priority)}</td>
                            <td>
                                <select
                                    className="form-select form-select-sm"
                                    value={todo.status}
                                    onChange={e => handleStatusChange(todo.id, e.target.value as Status)}
                                >
                                    <option value="NOT_STARTED">NOT_STARTED</option>
                                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                                    <option value="COMPLETED">COMPLETED</option>
                                </select>
                            </td>
                            <td>{FromUnixTime(todo.unixTime)}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <div className="d-flex justify-content-center mt-3">
                    <button
                        type="button"
                        className="btn btn-info px-4 fw-bold text-white"
                        onClick={() => navigate("/save")}
                    >
                        추가
                    </button>
                </div>
            </div>
        </div>
    );
};
