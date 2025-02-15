import { useForm } from "react-hook-form";
import { ToDoSaveType } from "../../type/ToDoType.tsx";
import {useNavigate, useParams} from "react-router-dom";
import {ToDoSubSaveType} from "../../type/ToDoSub.tsx";
import {ToDoSubSaveApi} from "../../api/ToDoSubApis.tsx";

export const ToDoSubSave = () => {
    const { parentId } = useParams();

    const navigate = useNavigate();
    const { register, handleSubmit } = useForm<ToDoSaveType>();

    const onSubmit = (data: ToDoSubSaveType) => {
        console.log(parentId)
        ToDoSubSaveApi(Number(parentId), data)
            .then(() => navigate(`/${parentId}`))
            .catch(err => console.log(err));
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="card p-3 shadow-sm" style={{ width: "400px", borderRadius: "10px" }}>
                <h4 className="text-center mb-3 fw-semibold">하위 내용 추가하기</h4>
                <form onSubmit={handleSubmit(onSubmit)}>
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
                    <button type="submit" className="btn btn-info btn-sm w-100 py-1 fw-bold text-white">저장</button>
                </form>
            </div>
        </div>
    );
};
