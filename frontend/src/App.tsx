import 'bootstrap/dist/css/bootstrap.min.css';

import './App.css'
import {Route, Routes} from "react-router-dom";
import {ToDoFindAll} from "./page/ToDoFindAll.tsx";
import {ToDoSave} from "./page/ToDoSave.tsx";
import {ToDoFindById} from "./page/ToDoFindById.tsx";
import {ToDoSubSave} from "./page/sub/ToDoSubSave.tsx";

function App() {
    return (
        <Routes>
            <Route path="/" element={<ToDoFindAll/>}/>
            <Route path="/save" element={<ToDoSave/>}/>
            <Route path="/:id" element={<ToDoFindById/>}/>
            <Route path="/subs/save/:parentId" element={<ToDoSubSave/>}/>
        </Routes>
    )
}

export default App
