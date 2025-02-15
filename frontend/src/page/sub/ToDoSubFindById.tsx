import {useEffect} from "react";
import {ToDoSubPutType} from "../../type/ToDoSub.tsx";
import {ToDoSubDeleteApi, ToDoSubFindByIdApi, ToDoSubPutApi} from "../../api/ToDoSubApis.tsx";
import {useForm} from "react-hook-form";

interface ToDoSubFindByIdProps {
    id: number;
    setModel: (id: number) => void;
}

export const ToDoSubFindById = ({ id, setModel }: ToDoSubFindByIdProps) => {
    const { register, handleSubmit, setValue } = useForm<ToDoSubPutType>();

    useEffect(() => {
        if (id) {
            ToDoSubFindByIdApi(Number(id))
                .then(res => {
                    setValue("title", res.title);
                    setValue("content", res.content);
                })
                .catch(err => console.log(err));
        }
    }, [id]);

    const onSubmit = (data: ToDoSubPutType) => {
        ToDoSubPutApi(Number(id), data)
            .then(() => setModel(0))
            .catch(err => console.log(err));
    };

    return (
        <div className="modal show d-block">
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content p-3">
                    <h4 className="text-center mb-3 fw-semibold text-dark">수정하기</h4>
                    <form onSubmit={handleSubmit(onSubmit)}>
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

                        <button type="submit" className="btn btn-info btn-sm w-100 py-1 fw-bold text-white">수정</button>
                        <button type="button" className="btn btn-danger btn-sm w-100 py-1 fw-bold text-white mt-2"
                                onClick={() => {
                                    ToDoSubDeleteApi(Number(id))
                                        .then(() => setModel(0))
                                        .catch(err => console.log(err));
                                }}>삭제
                        </button>
                        <button type="button" className="btn btn-secondary btn-sm w-100 py-1 fw-bold text-white mt-2"
                                onClick={() => setModel(0)}>
                            닫기
                        </button>
                    </form>
                </div>
            </div>
        </div>
    )
}
