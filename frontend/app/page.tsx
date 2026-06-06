'use client'
import { Button, Header, MapIcon, RoadIcon, WinIcon } from "@/components";
import Image from "next/image";
import Link from "next/link";

export default function Home() {
  return (
    <div>
      <Header headerText="Главное меню" />
      <div className="flex flex-col gap-10 justify-center items-center mx-4 my-2">
        <Link href={'./path-to'}><Button><div className="mr-auto text-left">Как добраться </div><MapIcon /></Button></Link>
        <Link href={'./path'}><Button><div className="mr-auto text-left">Проложить маршрут </div><RoadIcon /></Button></Link>
        <Link href={'./path-to'}><Button><div className="mr-auto text-left">Навигация по экспозиции </div><WinIcon /></Button></Link>

      </div>

    </div>
  );
}
