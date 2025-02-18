import {Link, Outlet, useLocation} from "react-router-dom";

export const Layout = () => {

    const location = useLocation();

    const isActive = (path: string): string => {
        if(location.pathname === path) return "active";
        return "";
    }

    return (
        <div>
            <header>
                <nav className="navbar navbar-expand-lg navbar-dark">
                    <div className="container-fluid">
                        <div className="collapse navbar-collapse justify-content-center" id="navbarNav">
                            <ul className="navbar-nav justify-content-center w-100">
                                <li className="nav-item">
                                    <Link className={`nav-link ${isActive("/")}`} aria-current="page" to="/">일정 보기</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className={`nav-link ${isActive("/save")}`} aria-current="page" to="/save">일정 추가</Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </header>

            <main>
                <Outlet/>
            </main>
        </div>
    );
};