"use client";

import { useState, useEffect } from "react";

export default function Header({
    headerText,
}: {
    headerText: string;
}) {
    const [largeFont, setLargeFont] = useState(false);

    useEffect(() => {
        const saved = localStorage.getItem("largeFont");
        if (saved) setLargeFont(saved === "true");
    }, []);

    useEffect(() => {
        localStorage.setItem("largeFont", String(largeFont));

        if (largeFont) {
            document.documentElement.classList.add("text-large");
        } else {
            document.documentElement.classList.remove("text-large");
        }
    }, [largeFont]);

    return (
        <header className="w-full border-b bg-[#151212]">
            <div className="px-4 md:px-6 py-4 flex flex-col sm:flex-row items-center justify-between gap-4">
                <div className="font-semibold text-lg text-center sm:text-left text-[#2DFF9B] [-webkit-text-stroke:1px_white]">
                    {headerText}
                </div>
            </div>
        </header>
    );
}