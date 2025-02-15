import { useForm } from "react-hook-form";
import { ToDoSaveType } from "../type/ToDoType.tsx";
import { ToUnixTime } from "../config/config.tsx";
import { ToDoSaveApi } from "../api/ToDoApis.tsx";
import { useNavigate } from "react-router-dom";

export const ToDoSave = () => {
    const navigate = useNavigate();
    const { register, handleSubmit } = useForm<ToDoSaveType>();

    const onSubmit = (data: ToDoSaveType) => {
        data.unixTime = ToUnixTime(data.unixTime);
        ToDoSaveApi(data)
            .then(() => navigate("/"))
            .catch(err => console.log(err));
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="card p-3 shadow-sm" style={{ width: "400px", borderRadius: "10px" }}>
                <h4 className="text-center mb-3 fw-semibold">추가하기</h4>
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
                    <div className="form-floating mb-2">
                        <input
                            className="form-control form-control-sm"
                            placeholder="Title"
                            {...register("title", { required: "필수 값 입니다" })}
                        />
                        <label htmlFor="floatingInput" className="small fw-bold">제목</label>
                    </div>
                    <div className="form-floating mb-2">
                        <textarea
                            className="form-control form-control-sm"
                            placeholder="Content"
                            style={{ height: "150px" }}
                            {...register("content", { required: "필수 값 입니다" })}
                        />
                        <label htmlFor="floatingInput" className="small fw-bold">내용</label>
                    </div>
                    <div className="mb-2">
                        <label className="fw-bold small">마감 날짜</label>
                        <input
                            type="date"
                            className="form-control form-control-sm"
                            {...register("unixTime", { required: "날짜와 시간을 입력하세요" })}
                        />
                    </div>
                    <button type="submit" className="btn btn-info btn-sm w-100 py-1 fw-bold text-white">저장</button>
                </form>
            </div>
        </div>
    );
};
