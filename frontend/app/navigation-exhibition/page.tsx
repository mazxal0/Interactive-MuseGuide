import { Button, Header } from "@/components";
import Link from "next/link";

export default function Page() {
    return <>
        <Header headerText="Навигация внутри выставок" />
        <div className="flex flex-col text-center justify-center items-center">
            <p>...</p>
            <p>Пока в разработке</p>
            <br />
            <Link href={'../'}> <Button>Назад</Button></Link>
        </div>

    </>
}