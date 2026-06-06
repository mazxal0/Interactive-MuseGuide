"use client";

import React from "react";

type ButtonProps = {
    children: React.ReactNode;
    onClick?: () => void;
    className?: string;
    disabled?: boolean;
    type?: "button" | "submit" | "reset";
};

export default function Button({
    children,
    onClick,
    className = "",
    disabled = false,
    type = "button",
}: ButtonProps) {
    return (
        <button
            type={type}
            onClick={onClick}
            disabled={disabled}
            className={`
                flex
                w-[95vw]
                px-1 py-2
                items-center
                gap-5
                bg-white text-black
                rounded
                hover:underline hover:scale-105
                active:scale-95
                transition
                disabled:opacity-50 disabled:cursor-not-allowed
                focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-black
                ${className}
            `}
        >
            {children}
        </button>
    );
}